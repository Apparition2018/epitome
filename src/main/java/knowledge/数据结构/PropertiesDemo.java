package knowledge.数据结构;

import org.junit.Test;

import java.util.Properties;

/**
 * Properties
 * <p>
 * Properties 继承 Hashtable，表示一个持久的属性集，属性列表的每个键及其对应的值都是一个字符串
 */
public class PropertiesDemo {

    /**
     * Properties([Properties defaults])
     * 创建一个带有指定默认值的空属性列表
     */
    @Test
    public void properties() {
        Properties capitals = new Properties();
        String str;

        capitals.put("Illinois", "Springfield");
        capitals.put("Missouri", "Jefferson City");
        capitals.put("Washington", "Olympia");
        capitals.put("California", "Sacramento");
        capitals.put("Indiana", "Indianapolis");

        str = capitals.getProperty("Florida", "Not Found");
        System.out.println("The capital of Florida is " + str + ".");

    }

    /**
     * String	getProperty(String key[, String defaultValue])
     * 用指定的键在属性列表中搜索属性
     */
    @Test
    public void getProperty() {
        properties();
    }

}
