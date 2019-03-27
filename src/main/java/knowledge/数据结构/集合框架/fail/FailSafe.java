package knowledge.数据结构.集合框架.fail;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * fail-safe
 * 安全失败
 * <p>
 * 采用安全失败机制的集合容器，在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历
 * <p>
 * java.util.concurrent 包下的容器都是安全失败，可以在多线程下并发使用，并发修改
 * <p>
 * http://www.cnblogs.com/songanwei/p/9387745.html
 */
public class FailSafe {

    @Test
    public void failSafe() {
        Map<String, String> premiumPhone = new ConcurrentHashMap<>();
        premiumPhone.put("Apple", "iPhone");
        premiumPhone.put("HTC", "HTC one");
        premiumPhone.put("Samsung", "S5");

        for (Object o : premiumPhone.keySet()) {
            System.out.println(premiumPhone.get(o)); // ConcurrentModificationException
            premiumPhone.put("Sony", "Xperia Z");
        }
    }

}
