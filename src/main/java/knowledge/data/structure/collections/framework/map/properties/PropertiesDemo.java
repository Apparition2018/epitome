package knowledge.data.structure.collections.framework.map.properties;

import l.demo.Demo;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Properties
 * Properties 继承 Hashtable。
 * Properties 类表示了一个持久的属性集。Properties 可保存在流中或从流中加载。属性列表中每个键及其对应值都是一个字符串。
 * http://www.runoob.com/java/java-properties-class.html
 * https://www.cnblogs.com/tonghun/p/7124245.html
 * <p>
 * void	    list(PrintStream out/PrintStream out)       将属性列表输出到指定的输出流
 *
 * @author ljh
 * created on 2020/9/7 19:43
 */
public class PropertiesDemo extends Demo {

    public static void main(String[] args) throws IOException {
        // Properties([Properties defaults])                创建一个带有指定默认值的空属性列表
        Properties props = new Properties();

        // void	            load(InputStream inStream)      从输入流中读取属性列表（键和元素对）
        // void	            load(Reader reader)             按简单的面向行的格式从输入字符流中读取属性列表（键和元素对）
        props.load(ClassLoader.getSystemResourceAsStream(JDBC_PROP_FILENAME));

        // Enumeration<?>	propertyNames()                 返回属性列表中所有键的枚举，如果在主属性列表中未找到同名的键，则包括默认属性列表中不同的键
        // Set<String>	    stringPropertyNames()           返回此属性列表中的键集，其中该键及其对应值是字符串，如果在主属性列表中未找到同名的键，则还包括默认属性列表中不同的键
        Enumeration<?> keys = props.propertyNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            // String	    getProperty(String key[, String defaultValue])  用指定的键在属性列表中搜索属性
            System.out.println(key + " = " + props.getProperty(key));
        }

        // Object	        setProperty(String key, String value)           调用 Hashtable 的方法 put
        props.setProperty("jdbc.test", "test");

        // void	            store(OutputStream out, String comments)
        // 以适合使用 load(InputStream) 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流
        // void	            store(Writer writer, String comments)
        // 以适合使用 load(Reader) 方法的格式，将此 Properties 表中的属性列表（键和元素对）写入输出字符
        // props.store(new FileOutputStream(JDBC_PROP_FILENAME), "testProperties");
    }

}
