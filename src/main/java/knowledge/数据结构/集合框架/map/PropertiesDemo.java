package knowledge.数据结构.集合框架.map;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Properties
 * <p>
 * Properties 继承 Hashtable，表示一个持久的属性集.属性列表中每个键及其对应值都是一个字符串
 * <p>
 * http://www.runoob.com/java/java-properties-class.html
 * https://www.cnblogs.com/tonghun/p/7124245.html
 */
public class PropertiesDemo {

    @Test
    public void test() throws IOException {
        String filePath = "src/main/java/knowledge/数据结构/集合框架/map/db.properties";

        FileInputStream fis = new FileInputStream(filePath);

        Properties props = new Properties();
        // void	load(InputStream inStream)
        // 从输入流中读取属性列表（键和元素对）
        props.load(fis);

        // String	getProperty(String key[, String defaultValue])
        // 用指定的键在属性列表中搜索属性
        p("drivername = " + props.getProperty("drivername")); // drivername = oracle.jdbc.driver.OracleDriver
        p("url = " + props.getProperty("url"));             // url = jdbc:oracle:thin:@localhost:1521:xe
        p("username = " + props.getProperty("username"));   // username = ljh
        p("password = " + props.getProperty("password"));   // password = 123456

        // Enumeration<?>	propertyNames()
        // 返回属性列表中所有键的枚举，如果在主属性列表中未找到同名的键，则包括默认属性列表中不同的键
        Enumeration keys = props.propertyNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            p(key + " = " + props.getProperty(key));
        }

        props.put("test", "test");

        FileOutputStream fos = new FileOutputStream(filePath);
        // void	store(OutputStream out, String comments)
        // 以适合使用 load(InputStream) 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流
        props.store(fos, "test");

        fis.close();
        fos.close();
    }

    public static <T> void p(T obj) {
        if (obj == null) {
            return;
        }
        System.out.println(obj);
    }

}
