package knowledge.jdbc;

import java.sql.*;

/**
 * JDBC (Java Database Connectivity)
 * <p>连接数据库的步骤：
 * <pre>
 * 1 加载类库（驱动 jar 包）到 JVM
 * 2 通过 DriverManager 建立连接，这里会加载 jar 包中 JDBC 实现类来与数据库建立连接
 * 3 通过 Connection 创建 Statement 对象，执行 SQL 语句
 * 4 若执行的是 DQL 语句，则会得到查询结果集 Result，遍历该结果集得到查询内容
 * 5 关闭连接
 * </pre>
 * <a href="https://www.cnblogs.com/progor/p/9096463.html">JDBC 的基本使用</a>
 *
 * @author ljh
 * @since 2020/10/10 15:25
 */
public class JDBC {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // 1. 加载驱动包
            // static Class<?>	    forName(String name[, boolean initialize, ClassLoader loader])
            // 使用给定的类加载器，返回与带有给定字符串名的类或接口相关联的 Class 对象
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 建立连接
            // static Connection	getConnection(String url, Properties info)
            // static Connection	getConnection(String url[, String user, String password])
            // 试图建立到给定数据库 URL 的连接
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/epitome?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "root");

            // 3. 创建 Statement
            // Statement	        createStatement(int resultSetType[, int resultSetConcurrency, int resultSetHoldability])
            // 创建一个 Statement 对象，该对象将生成具有给定类型、并发性和可保存性的 ResultSet 对象
            Statement statement = conn.createStatement();
            String dqlSql = "SELECT id, name, course, score FROM score";
            // 3.1 boolean          execute(String sql)             任何语句，一般执行 DDL 语句
            // 3.2 int	            executeUpdate(String sql)       DML 语句，返回一个整数，表示影响了多少条数据 
            // 3.3 ResultSet	    executeQuery(String sql)        DQL 语句，返回查询结果集
            ResultSet resultSet = statement.executeQuery(dqlSql);

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String course = resultSet.getString("course");
                String score = resultSet.getString("score");
                System.out.println(id + " " + name + course + (null != score ? "成绩为" + score : "没有成绩"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    // 5. 关闭连接
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
