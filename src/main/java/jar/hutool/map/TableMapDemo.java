package jar.hutool.map;

import cn.hutool.core.map.TableMap;

import static l.demo.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/TableMap/">TableMap</a> 可重复键 Map
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/map/TableMap.html">TableMap api</a>
 * <p>TableMap 支持键值重复，且正反向查找
 *
 * @author ljh
 * @since 2020/11/2 11:49
 */
public class TableMapDemo {

    public static void main(String[] args) {
        Integer[] keys = new Integer[]{1, 2, 2, 3, 3, 3};
        String[] values = new String[]{"A", "A", "A", "B", "B", "C"};
        TableMap<Integer, String> tableMap = new TableMap<>(keys, values);

        p(tableMap);                // TableMap{keys=[1, 2, 2, 3, 3, 3], values=[A, A, A, B, B, C]}

        p(tableMap.getValues(3));   // [B, B, C]
        p(tableMap.getKeys("A"));   // [1, 2, 2]
    }
}
