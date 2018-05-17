package S_10_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//必须包含/否则会导致服务器无法启动
@WebServlet(value = "/FindServlet")
public class FindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;
//        Statement ps = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
            String name = "root";
            String password = "dingzhixin";
            connection = DriverManager.getConnection(url, name, password);
            if (connection != null) {
                List<Book> list = new ArrayList<>();
                //--------------------------分页查询-------------------------------
                //第一个参数是查询结果的总条数的光标位置
                //第二参数为从第一个参数的光标位置起返回的查询信息条数
                //实例:总共30条数据,id,1-30,倒序查询,当第一个参数为0,第二个参数为10时返回30-21(含30和21)10条数据
                //当第一个参数为10,第二个参数为10时,返回20-11(含20和11)10条数据
                String sql="select * from books order by id desc limit ?,?";

                ps=connection.prepareStatement(sql);
                 ps.setInt(1,20);
                 ps.setInt(2,10);
                 result=ps.executeQuery();

                //--------------------------分页查询-------------------------------


                //--------------------使用数据库存储过程查询------------------------------------
//                CallableStatement cs=connection.prepareCall("{call findAllBooks()}");
//                result=cs.executeQuery();
                //--------------------使用数据库存储过程查询------------------------------------


              //----------------正常查询---------------------------------------------------
//                String sql = "select * from books";
//                ps = connection.createStatement();
//                result = ps.executeQuery(sql);
                //----------------正常查询---------------------------------------------------
                while (result.next()) {
                    Book book = new Book();
                    book.setAuthor(result.getString("author"));
                    book.setBookCount(result.getInt("bookCount"));
                    book.setId(result.getInt("id"));
                    book.setName(result.getString("name"));
                    book.setPrice(result.getDouble("price"));
                    list.add(book);
                }
                System.out.println("lsit的大小是--"+list.size());
                req.setAttribute("list", list);
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //重定向到指定页面,默认查找的是根目录web下的文件
        req.getRequestDispatcher("/10-1/book_list.jsp").forward(req, resp);

    }
}
