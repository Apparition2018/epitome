package knowledge.api.util.resourcebundle;

import org.junit.Test;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * ResourceBundle
 * <p>
 * 资源包包含特定于语言环境的对象。
 * 当程序需要一个特定于语言环境的资源时（如 String），程序可以从适合当前用户语言环境的资源包中加载它。
 * 使用这种方式，可以编写很大程度上独立于用户语言环境的程序代码，它将资源包中大部分（即便不是全部）特定于语言环境的信息隔离开来。
 * <p>
 * https://www.cnblogs.com/tonghun/p/7124245.html
 */
public class ResourceBundleDemo {

    @Test
    public void test() {

        String filePath = "src/main/java/knowledge/数据结构/集合框架/map/db.properties";

        // static ResourceBundle	getBundle(String baseName)
        // 使用指定的基本名称、默认的语言环境和调用者的类加载器获取资源包
        ResourceBundle bundle = ResourceBundle.getBundle(filePath);

        // String	getString(String key)
        // 从此资源包或它的某个父包中获取给定键的字符串
        p("drivername = " + bundle.getString("drivername"));
        p("url = " + bundle.getString("url"));
        p("username = " + bundle.getString("username"));
        p("password = " + bundle.getString("password"));

        // abstract  Enumeration<String>	getKeys()
        // 返回键的枚举
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            p(key + " = " + bundle.getString(key));
        }

    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }
}
