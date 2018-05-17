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

@WebServlet(value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

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
            if (connection != null) {
                int id = Integer.valueOf(req.getParameter("id"));
                int bookCount = Integer.valueOf(req.getParameter("bookCount"));
                String sql = "update books set bookCount=? where id=?";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, bookCount);
                ps.setInt(2, id);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    out.print("修改成功!");
                } else {
                    out.print("修改失败!");
                }
            } else {
                out.print("数据库连接失败!");
            }


        } catch (ClassNotFoundException | SQLException e) {
            out.print("数据库异常!");
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

        resp.sendRedirect("/FindServlet");


    }
}
