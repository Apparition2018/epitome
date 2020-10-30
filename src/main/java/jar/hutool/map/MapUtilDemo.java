package jar.hutool.map;

import cn.hutool.core.lang.Editor;
import cn.hutool.core.map.MapUtil;
import l.demo.Demo;
import org.junit.Test;

import java.util.Map;

/**
 * MapUtil
 * https://hutool.cn/docs/#/core/Map/Map%E5%B7%A5%E5%85%B7-MapUtil
 *
 * @author Arsenal
 * created on 2020/10/29 2:36
 */
public class MapUtilDemo extends Demo {

    @Test
    public void testMapUtil() {

        // filter(Map, Filter)                      过滤
        p(MapUtil.filter(map2, (Editor<Map.Entry<Integer, String>>) intStrEntry -> {
            if (intStrEntry.getKey() % 2 == 1) {
                return intStrEntry;
            }
            return null;
        })); // {1=A, 3=C, 5=D}
    }
}
