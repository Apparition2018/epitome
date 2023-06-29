package springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.dao.master.PrizeMapper;
import springboot.dao.master.UserMapper;
import springboot.domain.master.Prize;
import springboot.exception.ServiceException;

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
    /** 抽奖奖品无库存 KEY */
    private static final String DRAW_PRIZE_NO_STOCK_KEY = "draw:prize:noStock:%d";

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
     * @return 奖品ID，0表示不中奖
     */
    @GetMapping("test")
    @Transactional(rollbackFor = Exception.class)
    public Integer test(int userId) throws InterruptedException, JsonProcessingException {
        // 抽奖活动缓存
        int drawId = 1;
        Draw drawCache = this.getDrawCache(drawId);

        // 扣减积分
        int effect = userMapper.deductScore(userId, drawCache.getScore());
        if (effect == 0) throw new ServiceException("积分不足");

        // 抽奖
        Integer winId = this.draw(drawCache.getPrizeList());
        if (winId != 0) {
            // 查看奖品无库存缓存
            if (this.getPrizeNoStockCache(drawId, winId) == null) {
                // 奖品无库存缓存为 null，表示奖品有库存
                // 奖品中奖数+1
                synchronized (DrawController.class) {
                    effect = prizeMapper.incrWinQty(winId);
                }
                if (effect == 0) {
                    // 奖品中奖数+1失败，表示库存为0
                    // 设置奖品无库存缓存
                    this.setPrizeNoStockCache(drawId, winId);
                    winId = 0;
                }
            } else {
                // 奖品无库存缓存不为 null，表示奖品无库存
                winId = 0;
            }
        }

        return winId;
    }

    /**
     * 抽奖
     *
     * @param prizeList 奖品列表
     * @return 奖品ID，0表示不中奖
     */
    private Integer draw(List<Prize> prizeList) {
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
    private Draw getDrawCache(Integer drawId) throws JsonProcessingException {
        String key = String.format(DRAW_KEY, drawId);
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            String cacheStr = (String) redisTemplate.opsForValue().get(key);
            return objectMapper.readValue(cacheStr, Draw.class);
        } else {
            List<Prize> prizeList = prizeMapper.listIdAndPrByDrawId(drawId);
            Draw draw = new Draw().setId(drawId).setScore(1).setPrizeList(prizeList);
            String cacheStr = objectMapper.writeValueAsString(draw);
            redisTemplate.opsForValue().set(key, cacheStr, 1, TimeUnit.DAYS);
            return draw;
        }
    }

    /**
     * 获取奖品无库存缓存
     *
     * @param drawId 抽奖活动ID
     * @param winId  中奖奖品ID
     * @return 奖品无库存缓存
     */
    private Object getPrizeNoStockCache(int drawId, int winId) {
        return redisTemplate.opsForHash().get(String.format(DRAW_PRIZE_NO_STOCK_KEY, drawId), String.valueOf(winId));
    }

    /**
     * 设置奖品无库存缓存
     *
     * @param drawId 抽奖活动ID
     * @param winId  中奖奖品ID
     */
    private void setPrizeNoStockCache(int drawId, int winId) {
        String key = String.format(DRAW_PRIZE_NO_STOCK_KEY, drawId);
        redisTemplate.opsForHash().put(key, String.valueOf(winId), 0);
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
    }

    @Data
    @Accessors(chain = true)
    private static class Draw {
        private int id;
        /** 扣分积分 */
        private int score;
        List<Prize> prizeList;
    }
}
