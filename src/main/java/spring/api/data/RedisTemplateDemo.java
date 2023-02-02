package spring.api.data;

import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import l.demo.Demo;
import org.apache.commons.beanutils.PropertyUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import springboot.EpitomeApplication;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RedisTemplate
 *
 * @author ljh
 * @since 2021/5/26 15:05
 */
@SpringBootTest(classes = EpitomeApplication.class)
public class RedisTemplateDemo extends Demo {

    private static final String LIST_KEY = "List";
    private static final String MAP_KEY = "Map:%s";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testList() {
        // 删除指定键
        redisTemplate.delete(LIST_KEY);
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        // rightPushAll
        listOperations.rightPushAll(LIST_KEY, 1, 2, 3, 4, 5);
        // rightPush
        listOperations.rightPush(LIST_KEY, 6);
        // 设置指定索引的值
        listOperations.set(LIST_KEY, 5, 7);
        // 删除指定键指定个数的值
        listOperations.remove(LIST_KEY, 2, 3);
        // 设置过期时间
        redisTemplate.expire(LIST_KEY, 1, TimeUnit.MINUTES);
        pe("长度：" + listOperations.size(LIST_KEY));
        pe("获取指定范围值：" + listOperations.range(LIST_KEY, 0, -1));
        pe("获取指定索引的值：" + listOperations.index(LIST_KEY, 2));
        pe("获取指定值在列表第一次出现的索引：" + listOperations.indexOf(LIST_KEY, 2));
    }

    @Test
    public void testHash() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, Object> map = PropertyUtils.describe(personList.get(0));
        String key = String.format(MAP_KEY, personList.get(0).getId());
        // 设置多个键值对
        hashOperations.putAll(key, map);
        // 存在指定键，才设置值
        hashOperations.putIfAbsent(key, "id", 1);
        // 设置过期时间
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);

        pe("获取所有键：" + hashOperations.keys(key));
        pe("是否存在键：" + hashOperations.hasKey(key, "id"));
        pe("获取所有键值对：" + hashOperations.entries(key));
        pe("获取所有值：" + hashOperations.values(key));
        pe("获取指定键的值：" + hashOperations.multiGet(key, Lists.newArrayList("id", "name")));

        // 删除指定键的指定hasKeys
        hashOperations.delete(key, "otherInfo");
    }

    @Test
    public void testPipeline() {
        final String TASK1 = "normal";
        stopWatch.start(TASK1);
        for (int i = 1; i <= THOUSAND; i++) {
            redisTemplate.opsForValue().set(TASK1 + i, "i");
            redisTemplate.opsForValue().getAndDelete((TASK1 + i));
        }
        stopWatch.stop();

        final String TASK2 = "pipeline RedisCallback";
        stopWatch.start(TASK2);
        redisTemplate.executePipelined(
                (RedisCallback<Object>) connection -> {
                    connection.openPipeline();
                    for (int i = 1; i <= THOUSAND; i++) {
                        connection.set((TASK2 + i).getBytes(), String.valueOf(i).getBytes());
                        connection.del((TASK2 + i).getBytes());
                    }
                    connection.closePipeline();
                    return null;
                }
        );
        stopWatch.stop();

        final String TASK3 = "pipeline SessionCallback";
        stopWatch.start(TASK3);
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(@NotNull RedisOperations operations) throws DataAccessException {
                for (int i = 1; i <= THOUSAND; i++) {
                    operations.opsForValue().set(TASK3 + i, i);
                    operations.opsForValue().getAndDelete(TASK3 + i);
                }
                return null;
            }
        });
        stopWatch.stop();

        p(stopWatch.prettyPrint());
        // ---------------------------------------------
        // ns         %     Task name
        // ---------------------------------------------
        // 1270684900  082%  normal
        // 161263800  010%  pipeline RedisCallback
        // 108837100  007%  pipeline SessionCallback
    }

    @Test
    public void testStream() {
    }
}
