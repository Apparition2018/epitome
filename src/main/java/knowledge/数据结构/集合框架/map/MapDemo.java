package knowledge.数据结构.集合框架.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Map
 */
public class MapDemo {

    /**
     * 遍历
     */
    @Test
    public void traversal() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        // 方法一
        for (String key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }

        // 方法二
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        // 方法三
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }

        // 方法四
        map.forEach((k,v)-> System.out.println("key= " + k + " and value= " + v));

    }

    /**
     * Set<Map.Entry<K,V>>	entrySet()
     * 返回此映射中包含的映射关系的 Set 视图
     */
    @Test
    public void entrySet() {
        traversal();
    }

    /**
     * Set<K>	keySet()
     * 返回此映射中包含的键的 Set 视图
     */
    @Test
    public void keySet() {
        traversal();
    }

    /**
     * Collection<V>	values()
     * 返回此映射中包含的值的 Collection 视图
     */
    @Test
    public void values() {
        traversal();
    }

}
