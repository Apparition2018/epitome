package jar.apache.commons.dbcp2;

import knowledge.data.structure.collections.framework.map.properties.PropertiesUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

import static l.demo.Demo.JDBC_PROPS_FILENAME;

/**
 * DBUtil
 *
 * @author ljh
 * @since 2020/10/10 17:00
 */
public class DBUtil {

    private static final BasicDataSource dataSource;

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
            dataSource.setMaxWait(Duration.ofMillis(5000));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
