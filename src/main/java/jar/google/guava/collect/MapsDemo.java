package jar.google.guava.collect;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Maps
 * <p>
 * https://guava.dev/releases/snapshot-jre/api/docs/
 */
public class MapsDemo {

    @Test
    public void test() {
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        Map<Integer, Integer> map2 = Maps.newHashMap(map);
        System.out.println(map2);
    }

}