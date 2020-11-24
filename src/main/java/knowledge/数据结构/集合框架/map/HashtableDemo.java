package knowledge.数据结构.集合框架.map;

import l.demo.Demo;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Hashtable
 * Hashtable 继承 Dictionary，实现 Map
 * https://www.runoob.com/manual/jdk1.6/java/util/Hashtable.html
 * <p>
 * Map              HashMap                         Hashtable
 * 线程       Collections.synchronizedList(map);      同步
 * 键值为 null         不可以                         可以
 * hash             重新结算 hash                   直接使用对象的 hashcode
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class HashtableDemo extends Demo {

    public static void main(String[] args) {
        // Hashtable([int initialCapacity[, float loadFactor]]) 用指定初始容量和指定加载因子构造一个新的空哈希表。默认初始容量11，加载因子0.75
        // Hashtable(Map<? extends K,? extends V> t)            构造一个与给定的 Map 具有相同映射关系的新哈希表
        Hashtable<String, String> hashtable = new Hashtable<>();

        hashtable.put("1", "A");
        hashtable.put("2", "B");
        hashtable.put("3", "C");

        // boolean	        contains(Object value)  测试此映射表中是否存在与指定值关联的键
        p(hashtable.contains("A")); // ture

        // Enumeration<K>	keys()                  返回此哈希表中的键的枚举
        Enumeration<String> keyEnumeration = hashtable.keys();
        while (keyEnumeration.hasMoreElements()) {
            p("keys(): " + keyEnumeration.nextElement());
        }

        // Enumeration<V>	elements()              返回此哈希表中的值的枚举
        Enumeration<String> elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            p("elements(): " + elements.nextElement());
        }

    }

}
