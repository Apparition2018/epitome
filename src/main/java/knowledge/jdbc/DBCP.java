package knowledge.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBCP (Database Connection Pool)
 *
 * @author ljh
 * created on 2020/10/10 15:55
 */
public class DBCP {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
