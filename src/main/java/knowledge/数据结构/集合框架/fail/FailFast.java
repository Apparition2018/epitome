package knowledge.数据结构.集合框架.fail;

import org.junit.Test;

import java.util.*;

/**
 * fail-fast
 * 快速失败
 * <p>
 * 在用迭代器遍历一个集合对象时，如果遍历过程中对集合对象的内容进行了修改（增加、删除、修改），
 * 则会抛出Concurrent Modification Exception
 * <p>
 * http://www.cnblogs.com/songanwei/p/9387745.html
 */
public class FailFast {

    @Test
    public void test() {
        Map<String, String> premiumPhone = new HashMap<>();
        premiumPhone.put("Apple", "iPhone");
        premiumPhone.put("HTC", "HTC one");
        premiumPhone.put("Samsung", "S5");

        Iterator iterator = premiumPhone.keySet().iterator();

        while (iterator.hasNext()) {
            System.out.println(premiumPhone.get(iterator.next())); // ConcurrentModificationException
            premiumPhone.put("Sony", "Xperia Z");
        }
    }

}
