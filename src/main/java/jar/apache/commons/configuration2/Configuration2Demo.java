package jar.apache.commons.configuration2;

import l.demo.Demo;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Configuration2
 * https://commons.apache.org/proper/commons-configuration/userguide/user_guide.html
 *
 * @author ljh
 * @since 2020/11/15 1:06
 */
public class Configuration2Demo extends Demo {

    @Test
    public void testConfiguration2() throws ConfigurationException {
        Configurations configs = new Configurations();

        // .properties
        // https://blog.csdn.net/wanghantong/article/details/79072474
        PropertiesConfiguration propertiesConfiguration = configs.properties(new File(JDBC_PROP_FILENAME));
        p(propertiesConfiguration.getString("jdbc.driver"));

        // .xml
        // http://www.voidcn.com/article/p-kusllija-bqy.html
        XMLConfiguration xmlConfiguration = configs.xml(DEMO_PATH + "demo.xml");
        // 使用默认的符号定义创建一个表达式引擎
        DefaultExpressionEngine defaultExpressionEngine = new DefaultExpressionEngine(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
        xmlConfiguration.setExpressionEngine(defaultExpressionEngine);
        p(xmlConfiguration.getString("student[@id]"));      // 1
        p(xmlConfiguration.getList("student.name"));        // [张三, 李四]
        p(xmlConfiguration.getList("student.name").get(0)); // 张三
    }
}
