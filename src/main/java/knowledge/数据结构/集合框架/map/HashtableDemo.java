package knowledge.数据结构.集合框架.map;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

/**
 * Hashtable
 * <p>
 * Hashtable 继承 Dictionary，实现 Map
 * Hashtable 是同步的
 */
public class HashtableDemo {

    /**
     * Hashtable([int initialCapacity, float loadFactor])
     * 用指定初始容量和指定加载因子构造一个新的空哈希表
     * 默认初始容量：11
     * 默认加载因子：0.75
     * <p>
     * Hashtable(Map<? extends K,? extends V> t)
     * 构造一个与给定的 Map 具有相同映射关系的新哈希表
     */
    @Test
    public void test() {
        Hashtable<String, Double> balance = new Hashtable<>();
        Enumeration names;
        Set keySet;
        String str;
        double d;

        balance.put("Zara", 3434.34);
        balance.put("Mahnaz", 123.22);
        balance.put("Ayan", 1378.00);
        balance.put("Daisy", 99.22);
        balance.put("Qadir", -19.08);

        // Enumeration<K>	keys()
        // 返回此哈希表中的键的枚举
        names = balance.keys();
        while (names.hasMoreElements()) {
            str = (String) names.nextElement();
            System.out.println(str + ": " + balance.get(str));
        }

        System.out.println("********************");

        // Set<K>	keySet()
        // 返回此映射中包含的键的 Set 视图
        keySet = balance.keySet();
        for (Object aKeySet : keySet) {
            str = (String) aKeySet;
            System.out.println(str + ": " + balance.get(str));
        }

        System.out.println("********************");

        // Enumeration<V>	elements()
        // 返回此哈希表中的值的枚举
        names = balance.elements();
        while (names.hasMoreElements()) {
            d = (double) names.nextElement();
            System.out.println(d);
        }

    }

}
