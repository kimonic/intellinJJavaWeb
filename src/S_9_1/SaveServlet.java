package S_9_1;



import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@SuppressWarnings("SqlResolve")
@WebServlet(name = "saveServlet", urlPatterns = "/SaveServlet")
public class SaveServlet extends HttpServlet {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "dingzhixin";
    private Connection conn = null;
    private Statement stmt = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        在 Servlet 的生命期中，仅执行一次 init() 方法。它是在服务器装入 Servlet
//        时执行的。可以配置服务器，以在启动服务器或客户机首次访问 Servlet 时装入 Servlet。
//        无论有多少客户机访问Servlet，都不会重复执行 init() 。
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
//            System.out.println(" 实例化Statement对象...");
//            stmt = conn.createStatement();
//            String sql, sq2;
//            sql = "SELECT Token, username, password FROM users";

//            sq2 = "select userbookmark.Type, users.nickname from userbookmark  \r\n" +
//                    "inner join  users " +
//                    "on userbookmark.Token = users.Token where "
//                    + "userbookmark.Token=2;";

//            ResultSet rs = stmt.executeQuery(sql);
//            // 展开结果集数据库
//            while(rs.next()){
//                // 通过字段检索
////                int id  = rs.getInt("Token");
////                String name = rs.getString("UserName");
////                String url = rs.getString("PassWord");
//                String type=rs.getString("type");
//                String nickname=rs.getString("nickname");
//
//                // 输出数据
//                System.out.print("type: " + type);
//                System.out.print(",nickname: " + nickname);
//
////                System.out.print("Token: " + id);
////                System.out.print(",UsersName: " + name);
////                System.out.print(", PassWord: " + url);
//                System.out.print("\n");
//            }
//            // 完成后关闭
//            rs.close();
//            stmt.close();
//            conn.close();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
//        finally {
//            // 关闭资源
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException se2) {
//                se2.printStackTrace();
//            }// 什么都不做
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String sex=req.getParameter("sex");
        String question=req.getParameter("question");
        String answer=req.getParameter("answer");
        String eamil=req.getParameter("eamil");
        PreparedStatement ps=null;
        if (conn!=null){
            try {
                String sql="insert into users(username,password,sex,question,answer,email)" +
                        " values(?,?,?,?,?,?)";
                ps=conn.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2,password);
                ps.setString(3,sex);
                ps.setString(4,question);
                ps.setString(5,answer);
                ps.setString(6,eamil);
                ps.executeUpdate();

                PrintWriter out=resp.getWriter();
                out.print("<h1 align='center'>");
                out.print(username+"注册成功!");
                out.print("</h1>");
                out.flush();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
//            finally {
//            // 关闭资源
//            try {
//                if (ps != null) ps.close();
//            } catch (SQLException se2) {
//                se2.printStackTrace();
//            }// 什么都不做
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
        }else {
            resp.sendError(500,"数据库链接错误!");
        }

    }
}
