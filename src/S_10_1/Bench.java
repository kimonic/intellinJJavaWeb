package S_10_1;

import com.sun.jmx.snmp.SnmpString;
import sun.security.util.Password;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bench {

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
            String name = "root";
            String password = "dingzhixin";
            connection = DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

        return connection;
    }

    @SuppressWarnings("Duplicates")
    public int saveBench() {
        int row = 0;
        Connection connection = getConnection();
        String sql = "insert into students(id,name,sex,age) values(?,?,?,?);";
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 100; i++) {
                ps.setInt(1, i);
                ps.setString(2, "小小" + i);
                ps.setString(3, i % 2 == 1 ? "男" : "女");
                ps.setInt(4, i + 10);
                ps.addBatch();
            }
            //rows数组对应每条SQL语句影响的行数??
            int[] rows = ps.executeBatch();
            for (int s : rows) {
                System.out.println(rows[s]);
            }
            row = rows.length;
            System.out.println("更新了" + row + "条数据!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps!=null){
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return row;
    }


}
