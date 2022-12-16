package spring.api.util;

import knowledge.api.lang.SystemDemo;
import knowledge.data.structure.collections.framework.map.properties.PropertiesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * SpringProperties
 *
 * @author ljh
 * @since 2021/9/9 9:01
 */
public class SpringProperties {

    private static final String PROPERTIES_NAME = "school.properties";

    /**
     * PropertyPlaceholderHelper
     * 属性文件占位符助手
     */
    @Test
    public void testPropertyPlaceholderHelper() {
        String s = "{school.student[0].name} {school.student[0].age} {school.student[0].score}";
        Properties properties = PropertiesUtil.loadProps(PROPERTIES_NAME);

        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("{", "}");
        System.out.println(propertyPlaceholderHelper.replacePlaceholders(s, properties)); // Jack 18 100
    }

    /**
     * SystemPropertyUtils
     * 系统属性 {@link SystemDemo#getProperty()} 工具
     */
    @Test
    public void testSystemPropertyUtils() {
        String s = "My computer os is ${user.name}";
        System.out.println(SystemPropertyUtils.resolvePlaceholders(s)); // My computer os is Administrator
    }

    @Test
    public void testPropertiesLoaderUtils() throws IOException {
        // static Properties    loadProperties(Resource resource)
        // 加载指定 Resource 的 properties 文件
        Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource(PROPERTIES_NAME));
        // static Properties    loadProperties(EncodedResource resource)
        // 加载指定 EncodedResource 的 properties 文件
        props = PropertiesLoaderUtils.loadProperties(new EncodedResource(
                new ClassPathResource(PROPERTIES_NAME), StandardCharsets.UTF_8));

        // static Properties    loadAllProperties(String resourceName)
        // 加载当前 classpath 下所有相同名称的 properties 文件
        props = PropertiesLoaderUtils.loadAllProperties(PROPERTIES_NAME);
    }
}
