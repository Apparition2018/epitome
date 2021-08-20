package spring.api.data;

import com.google.common.collect.Lists;
import l.demo.Demo;
import l.demo.Person;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import springboot.EpitomeApplication;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RedisTemplate
 *
 * @author ljh
 * created on 2021/5/26 15:05
 */
@SpringBootTest(classes = EpitomeApplication.class)
public class RedisTemplateDemo extends Demo {

    private static final String KEY = "Person:1";

    @Resource
    private RedisTemplate<String, Person> redisTemplate;

    @Test
    public void hash() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> map = PropertyUtils.describe(personList.get(0));
        // 设置多个键值对
        redisTemplate.opsForHash().putAll(KEY, map);
        // 存在指定 key，才设置 value
        redisTemplate.opsForHash().putIfAbsent(KEY, "id", 1);
        // 设置过期时间
        redisTemplate.expire(KEY, 1, TimeUnit.MINUTES);

        System.out.println("获取所有 keys：" + redisTemplate.opsForHash().keys(KEY));
        System.out.println("是否存在 key：" + redisTemplate.opsForHash().hasKey(KEY, "id"));
        System.out.println("获取所有键值对：" + redisTemplate.opsForHash().entries(KEY));
        System.out.println("获取所有 values：" + redisTemplate.opsForHash().values(KEY));
        System.out.println("获取指定 key 的 value：" + redisTemplate.opsForHash().multiGet(KEY, Lists.newArrayList("id", "name")));

        // 删除指定 key
        redisTemplate.opsForHash().delete(KEY, "otherInfo");
    }

}
