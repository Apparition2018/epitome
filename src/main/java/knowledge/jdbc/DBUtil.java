package knowledge.jdbc;

import knowledge.数据结构.集合框架.map.properties.PropertiesUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBUtil
 *
 * @author ljh
 * created on 2020/10/10 17:00
 */
public class DBUtil {

    private static BasicDataSource dataSource;

    static {
        try {
            Properties props = PropertiesUtil.loadProps("jdbc.properties");
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
