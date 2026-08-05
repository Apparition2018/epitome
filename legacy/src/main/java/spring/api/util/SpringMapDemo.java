package spring.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Spring Map
 *
 * @author ljh
 * @since 2021/9/8 10:25
 */
public class SpringMapDemo extends Demo {

    /** LinkedCaseInsensitiveMap    键不区分大小写 */
    @Test
    public void testLinkedCaseInsensitiveMap() {
        Map<String, Integer> map = new LinkedCaseInsensitiveMap<>();
        map.put("a", 1);
        p(map.get("A")); // 1
    }

    /** LinkedMultiValueMap         1键-多值 Map */
    @Test
    public void testLinkedMultiValueMap() {
        LinkedMultiValueMap<Integer, String> map = new LinkedMultiValueMap<>();
        map.add(1, "a");
        map.add(1, "b");
        map.add(1, "c");
        p(map); // {1=[a, b, c]}
    }

    /**
     * ConcurrentReferenceHashMap
     * 键值使用软引用或弱引用的 ConcurrentHashMap，键值支持 null
     */
    @Test
    public void testConcurrentReferenceHashMap() throws InterruptedException {
        // 可以指定软引用，弱引用
        Map<String, Object> concurrentReferenceHashMap = new ConcurrentReferenceHashMap<>(10, ConcurrentReferenceHashMap.ReferenceType.WEAK);
        concurrentReferenceHashMap.put("key", "value");
        p(concurrentReferenceHashMap); // {key=value}

        System.gc();

        TimeUnit.SECONDS.sleep(1);
        p(concurrentReferenceHashMap); // {}
    }
}
