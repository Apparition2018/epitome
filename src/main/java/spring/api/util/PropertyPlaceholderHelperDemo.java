package spring.api.util;

import knowledge.data.structure.collections.framework.map.properties.PropertiesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Properties;

/**
 * PropertyPlaceholderHelper
 * 属性文件占位符助手
 *
 * @author ljh
 * created on 2021/9/8 11:22
 */
public class PropertyPlaceholderHelperDemo {

    @Test
    public void testPropertyPlaceholderHelper() {
        String s = "{school.student[0].name} {school.student[0].age} {school.student[0].score}";
        Properties properties = PropertiesUtil.loadProps("school.properties");

        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("{", "}");
        System.out.println(propertyPlaceholderHelper.replacePlaceholders(s, properties)); // Jack 18 100
    }
}
