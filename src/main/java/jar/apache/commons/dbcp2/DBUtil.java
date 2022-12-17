package jar.apache.commons.dbcp2;

import knowledge.data.structure.collections.framework.map.properties.PropertiesUtil;
import l.demo.Demo;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBUtil
 *
 * @author ljh
 * @since 2020/10/10 17:00
 */
public class DBUtil extends Demo {

    private static BasicDataSource dataSource;

    static {
        try {
            Properties props = PropertiesUtil.loadProps(JDBC_PROPS_FILENAME);
            dataSource = new BasicDataSource();
            // Class.forName()
            dataSource.setDriverClassName(props.getProperty("jdbc.driver"));
            // DriverManager.getConnection()
            dataSource.setUrl(props.getProperty("jdbc.url"));
            dataSource.setUsername(props.getProperty("jdbc.username"));
            dataSource.setPassword(props.getProperty("jdbc.password"));
            dataSource.setMaxTotal(10);
            dataSource.setMaxWaitMillis(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
