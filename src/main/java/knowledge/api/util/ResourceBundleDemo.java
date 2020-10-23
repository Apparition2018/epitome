package knowledge.api.util;

import l.demo.Demo;
import org.junit.Test;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * ResourceBundle
 * 资源包包含特定于语言环境的对象。
 * 当程序需要一个特定于语言环境的资源时（如 String），程序可以从适合当前用户语言环境的资源包中加载它。
 * 使用这种方式，可以编写很大程度上独立于用户语言环境的程序代码，它将资源包中大部分（即便不是全部）特定于语言环境的信息隔离开来。
 * <p>
 * https://jdk6.net/util/ResourceBundle.html
 * https://www.cnblogs.com/tonghun/p/7124245.html
 */
public class ResourceBundleDemo extends Demo {

    @Test
    public void test() {

        // static ResourceBundle	getBundle(String baseName[, ...])
        // 使用指定的基本名称...加载器获取资源包
        // 不需要 .properties 后缀
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

        // String	                getString(String key)
        // 从此资源包或它的某个父包中获取给定键的字符串
        p("driver = " + bundle.getString("jdbc.driver"));
        p("url = " + bundle.getString("jdbc.url"));
        p("username = " + bundle.getString("jdbc.username"));
        p("password = " + bundle.getString("jdbc.password"));

        // abstract  Enumeration<String>	getKeys()
        // 返回键的枚举
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            p(key + " = " + bundle.getString(key));
        }
        
    }
}
