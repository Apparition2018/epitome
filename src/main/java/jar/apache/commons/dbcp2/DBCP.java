package jar.apache.commons.dbcp2;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <a href="https://commons.apache.org/proper/commons-dbcp/">DBCP (Database Connection Pool)</a>
 * <pre>
 * JDBC: JAVA 连接数据库和执行 SQL 语句的 API
 * DBCP: 依赖 Jakarta commons-pool2 对象池机制的数据库连接池
 * </pre>
 *
 * @author ljh
 * @since 2020/10/10 15:55
 */
public class DBCP {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            // 默认 为 true，自动提交事务
            conn.setAutoCommit(false);
            String sql = "INSERT INTO score (name, course, score) VALUES (?, ?, ?)";
            // PreparedStatement，预编译的 SQL 对象，可执行含有动态信息的 SQL 语句，动态信息用 ? 代替
            // 由于 PreparedStatement 不会将动态信息拼接到 SQL 中，所以可以防止 SQL 注入攻击
            ps = conn.prepareStatement(sql);
            {
                ps.setString(1, "王五");
                ps.setString(2, "语文");
                ps.setInt(3, 81);
                // 添加到批操作缓存
                ps.addBatch();
            }
            {
                ps.setString(1, "王五");
                ps.setString(2, "数学");
                ps.setInt(3, 100);
                ps.addBatch();
            }
            int[] data = ps.executeBatch();
            if (ArrayUtils.contains(data, 0)) {
                conn.rollback();
            } else {
                conn.commit();
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            if (null != conn) {
                try {
                    conn.rollback();
                    conn.setAutoCommit(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            JdbcUtils.closeStatement(ps);
            DataSourceUtils.releaseConnection(conn, DBUtil.getDataSource());
        }
    }
}
