package springboot.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.dao.master.PrizeMapper;
import springboot.dao.master.UserMapper;
import springboot.domain.master.Prize;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * DrawController
 *
 * @author ljh
 * created on 2022/7/4 0:28
 */
@Slf4j
@RestController
@RequestMapping("/draw")
@Tag(name = "Draw")
public class DrawController {

    private final UserMapper userMapper;
    private final PrizeMapper prizeMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String DRAW_KEY = "draw:%s";
    private static final String DRAW_WIN_KEY = "draw:win:%s";

    public DrawController(UserMapper userMapper, PrizeMapper prizeMapper, RedisTemplate<String, Object> redisTemplate) {
        this.userMapper = userMapper;
        this.prizeMapper = prizeMapper;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/test")
    @Transactional(rollbackFor = Exception.class)
    public Integer test(Form form) throws InterruptedException {
        // TODO-LJH: 2022/7/25
        int drawId = 1;

        int effect = userMapper.deductScore(form.getUserId(), 1);
        if (effect != 1) {
            // 积分不足
            return -1;
        }

        Draw draw = this.getDraw(drawId);
        //Integer winId = this.draw(draw.getPrizeList());
        Integer winId = 2;
        if (winId != 0) {
            effect = prizeMapper.incrWinQty(winId);
            if (effect != 1) {
                return 0;
            }
            redisTemplate.opsForValue().increment(String.format(DRAW_WIN_KEY, 1));
            this.setDrawCache(drawId);
        }

        if (form.getUserId() == 1) {
            throw new RuntimeException("");
        }
        return winId;
    }

    private Integer draw(List<Prize> prizeList) {
        int random = ThreadLocalRandom.current().nextInt(10000);
        int flag = 0;
        for (Prize prize : prizeList) {
            if (random < (flag += prize.getPr())) {
                return prize.getId();
            }
        }
        return 0;
    }

    private Draw getDraw(Integer drawId) throws InterruptedException {
        String key = String.format(DRAW_KEY, drawId);
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            return (Draw) redisTemplate.opsForValue().get(key);
        }
        return this.setDrawCache(drawId);
    }

    private Draw setDrawCache(Integer drawId) throws InterruptedException {
        String key = String.format(DRAW_KEY, drawId);
        List<Prize> prizeList = prizeMapper.listByDrawId(drawId);
        TimeUnit.MILLISECONDS.sleep(10);
        Draw draw = new Draw();
        draw.setId(drawId);
        draw.setPrizeList(prizeList);
        int noPrizePr = 10000;
        for (Prize prize : prizeList) {
            noPrizePr -= prize.getPr();
        }
        Prize noPrize = new Prize()
                .setId(0)
                .setDrawId(1)
                .setPr(noPrizePr);
        prizeList.add(0, noPrize);
        redisTemplate.opsForValue().set(key, draw);
        return draw;
    }

    @Data
    private static class Form {
        private Integer userId;
    }

    @Data
    public static class Draw {
        private Integer id;
        List<Prize> prizeList;
    }
}
