package knowledge.数据结构.集合框架.map;

import l.demo.Demo;
import org.junit.Test;

import java.util.Map;

/**
 * Map
 * https://jdk6.net/util/Map.html
 * <p>
 * void	    putAll(Map<? extends K,? extends V> m)  从指定映射中将所有映射关系复制到此映射中（可选操作）
 * V	    remove(Object key)                      如果存在一个键的映射关系，则将其从此映射中移除（可选操作）
 * void	    clear()                                 从此映射中移除所有映射关系（可选操作）
 * <p>
 * boolean  containsKey(Object key)                 如果此映射包含指定键的映射关系，则返回 true
 * boolean  containsValue(Object value)             如果此映射将一个或多个键映射到指定值，则返回 true
 * boolean  isEmpty()                               如果此映射未包含键-值映射关系，则返回 true
 */
public class MapDemo extends Demo {

    /**
     * 遍历
     */
    @Test
    public void traversal() {
        // 方法一
        // Set<K>	                keySet()        返回此映射中包含的键的 Set 视图
        for (String key : map.keySet()) {
            System.out.println("key = " + key + " and value = " + map.get(key));
        }

        // 方法二
        // Set<Map.Entry<K,V>>	    entrySet()      返回此映射中包含的映射关系的 Set 视图
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + " and value = " + entry.getValue());
        }

        // 方法三
        // Collection<V>	        values()        返回此映射中包含的值的 Collection 视图
        for (String v : map.values()) {
            System.out.println("value = " + v);
        }

        // 方法四
        map.forEach((k, v) -> System.out.println("key = " + k + " and value = " + v));
    }

}
