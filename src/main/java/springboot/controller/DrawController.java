package springboot.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.domain.master.draw.Prize;
import springboot.exception.ServiceException;
import springboot.mapper.master.draw.PrizeMapper;
import springboot.mapper.master.draw.UserMapper;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * DrawController
 *
 * @author ljh
 * @since 2022/7/4 0:28
 */
@Slf4j
@RestController
@RequestMapping("draw")
@Tag(name = "Draw")
public class DrawController {

    /** 抽奖活动 KEY */
    private static final String DRAW_KEY = "draw:%d";

    private final UserMapper userMapper;
    private final PrizeMapper prizeMapper;
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public DrawController(UserMapper userMapper, PrizeMapper prizeMapper, ObjectMapper objectMapper, RedisTemplate<String, Object> redisTemplate) {
        this.userMapper = userMapper;
        this.prizeMapper = prizeMapper;
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * <a href="http://localhost:3333/draw/test?userId=1">抽奖测试</a>
     *
     * @param userId 用户ID
     * @return 奖品ID，0表示各种异常
     */
    @GetMapping("test")
    public Integer test(int userId) {
        int drawId = 1;
        // 抽奖活动缓存
        Draw drawCache = this.getDrawCache(drawId);

        try {
            return ((DrawController) AopContext.currentProxy()).executeDraw(userId, drawCache);
        } catch (ServiceException e) {
            log.warn("用户{}抽奖失败: {}", userId, e.getMessage());
            return 0;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer executeDraw(int userId, Draw draw) {
        // 1. 扣减积分
        int userEffect = userMapper.deductScore(userId, draw.getScore());
        // 2. 积分不足
        if (userEffect == 0) return 0;
        // 3. 抽奖
        Integer winId = this.doDraw(draw.getPrizeList());
        // 4. 没中奖
        if (winId == 0) return 0;
        // 5. 中奖，增加中奖数
        int prizeEffect = prizeMapper.incrWinQty(winId);
        // Cache Aside，删除缓存
        redisTemplate.delete(String.format(DRAW_KEY, draw.getId()));
        // 返回奖品ID
        if (prizeEffect != 0) return winId;
        // 6. 增加中奖数失败
        return 0;
    }

    /**
     * 抽奖
     *
     * @param prizeList 奖品列表
     * @return 奖品ID，0表示不中奖
     */
    private Integer doDraw(List<Prize> prizeList) {
        int random = ThreadLocalRandom.current().nextInt(10000);
        int threshold = 0;
        for (Prize prize : prizeList) {
            if (random < (threshold += prize.getProbability())) return prize.getId();
        }
        return 0;
    }

    /**
     * 获取抽奖活动缓存
     *
     * @param drawId 抽奖活动ID
     * @return 抽奖活动
     */
    private Draw getDrawCache(Integer drawId) {
        String key = String.format(DRAW_KEY, drawId);
        Object cachedObj = redisTemplate.opsForValue().get(key);
        if (cachedObj != null) {
            return objectMapper.readValue((String) cachedObj, Draw.class);
        }
        synchronized (this) {
            // 双重检查锁，防止缓存击穿
            cachedObj = redisTemplate.opsForValue().get(key);
            if (cachedObj != null) {
                return objectMapper.readValue((String) cachedObj, Draw.class);
            }

            List<Prize> prizeList = prizeMapper.listHasStockByDrawId(drawId);
            Draw draw = new Draw().setId(drawId).setScore(1).setPrizeList(prizeList);
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(draw), 1, TimeUnit.DAYS);
            return draw;
        }
    }

    @Data
    @Accessors(chain = true)
    public static class Draw {
        private int id;
        /** 每次抽奖扣减的积分 */
        private int score;
        List<Prize> prizeList;
    }
}
