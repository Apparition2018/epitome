package jar.hutool.map;

import cn.hutool.core.map.TableMap;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * TableMap     可重复键 Map
 * TableMap 支持键值重复，且正反向查找
 * https://hutool.cn/docs/#/core/Map/%E5%8F%AF%E9%87%8D%E5%A4%8D%E9%94%AE%E5%80%BCMap-TableMap
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/map/TableMap.html
 *
 * @author ljh
 * created on 2020/11/2 11:49
 */
public class TableMapDemo extends Demo {
    
    @Test
    public void testTableMap() {
        Integer[] keys = new Integer[] {1, 2, 2, 3, 3, 3};
        String[] values = new String[] {"A", "A", "A", "B", "B", "C"};
        TableMap<Integer, String> tableMap = new TableMap<>(keys, values);
        
        p(tableMap);                // TableMap{keys=[1, 2, 2, 3, 3, 3], values=[A, A, A, B, B, C]}
        
        p(tableMap.getValues(3));   // [B, B, C]
        p(tableMap.getKeys("A"));   // [1, 2, 2]
    }
}
