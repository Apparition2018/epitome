package spring.api.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ConcurrentReferenceHashMap
 * 一个对键和值都使用软引用或弱引用的 ConcurrentHashMap，键值支持 null
 * <p>
 * ConcurrentReferenceHashMap 深度解读：https://blog.csdn.net/weixin_43935907/article/details/106853430
 *
 * @author ljh
 * created on 2021/9/8 0:16
 */
public class ConcurrentReferenceHashMapDemo {

    @Test
    public void testConcurrentReferenceHashMap() throws InterruptedException {
        // 可以指定软引用，弱引用
        Map<String, Object> concurrentReferenceHashMap = new ConcurrentReferenceHashMap<>(10, ConcurrentReferenceHashMap.ReferenceType.WEAK);
        concurrentReferenceHashMap.put("key", "value");
        System.out.println(concurrentReferenceHashMap); // {key=value}

        System.gc();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(concurrentReferenceHashMap); // {}
    }
}
