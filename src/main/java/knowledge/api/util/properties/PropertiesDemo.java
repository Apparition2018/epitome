package knowledge.api.util.properties;

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

    private String filePath = "src/main/java/knowledge/数据结构/集合框架/map/";
    private Properties props = new Properties();

    /**
     * void	    load(InputStream inStream)  从输入流中读取属性列表（键和元素对）
     * void	    load(Reader reader)         按简单的面向行的格式从输入字符流中读取属性列表（键和元素对）
     */
    public PropertiesDemo() throws IOException {
        FileInputStream fis = new FileInputStream(filePath + "test.properties");
        props.load(fis);
        fis.close();
    }

    /**
     * String	getProperty(String key[, String defaultValue])
     * 用指定的键在属性列表中搜索属性
     */
    @Test
    public void getProperty() {
        p("A = " + props.getProperty("A")); // A = 1
        p("D = " + props.getProperty("D")); // D = null
        p("D = " + props.getProperty("D", "D")); // D = D
    }

    /**
     * Enumeration<?>	propertyNames()
     * 返回属性列表中所有键的枚举，如果在主属性列表中未找到同名的键，则包括默认属性列表中不同的键
     */
    @Test
    public void propertyNames() {
        Enumeration keys = props.propertyNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            p(key + " = " + props.getProperty(key));
        }
    }

    /**
     * void	store(OutputStream out, String comments)
     * 以适合使用 load(InputStream) 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流
     */
    @Test
    public void store() throws IOException {
        props.setProperty("test", "test");

        FileOutputStream fos = new FileOutputStream(filePath + "test2.properties");
        props.store(fos, "test");
        fos.close();
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
