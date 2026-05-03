package knowledge.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC (Java Database Connectivity)
 *
 * @author ljh
 * @since 2020/10/10 15:25
 */
public class JDBC {

    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream in = JDBC.class.getResourceAsStream("/application-druid.properties")) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = props.getProperty("spring.datasource.master.url");
        String username = props.getProperty("spring.datasource.master.username");
        String password = props.getProperty("spring.datasource.master.password");

        try (
            // 1. 建立连接
            // SPI 自动加载机制，允许第三方库在 JAR 包中声明“我提供了一个 JDBC 驱动”，无需再写 Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            // 2. 创建 Statement 对象，并执行语句
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, course, score FROM score")
        ) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String course = resultSet.getString("course");
                String score = resultSet.getString("score");
                System.out.printf("%d %s %s %s%n", id, name, course, score != null ? "成绩 " + score : "没有成绩");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
