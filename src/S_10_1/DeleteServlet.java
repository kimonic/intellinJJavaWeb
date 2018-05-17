package S_10_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;
        PreparedStatement ps = null;
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
            String name = "root";
            String password = "dingzhixin";
            connection = DriverManager.getConnection(url, name, password);
            int id = Integer.valueOf(req.getParameter("id"));
            String sql = "delete from books where id=?";
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    out.print("删除成功!");
                } else {
                    out.print("删除失败!");
                }

            } else {
                out.print("数据库删除失败!");
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.print("数据库连接异常!");
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
        resp.sendRedirect("/FindServlet");
    }
}
