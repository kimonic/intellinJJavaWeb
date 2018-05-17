<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/17
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>将添加的图书信息写入数据库</title>
</head>
<body>
<jsp:useBean id="book" class="S_10_1.Book"/>
<jsp:setProperty name="book" property="*"/>
<%
    Connection conn = null;
    Statement statement = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
        String name = "root";
        String password = "dingzhixin";
        conn = DriverManager.getConnection(url, name, password);
        //与插入语句同时执行,可以保证插入失败时自增键不会增长
        //对于有唯一键的数据表操作最好还是先查询是否存在唯一键进行预判断
        String sq="ALTER TABLE books AUTO_INCREMENT =1";
//        alter table 表名  AUTO_INCREMENT=1;

        String sql =
               "insert into books(name ,price,author,bookCount) values ('"
                       +book.getName()+"',"
                       +book.getPrice()+",'"
                       +book.getAuthor()+"',"
                        +book.getBookCount()+
                       ");"
                ;
        if (conn != null) {
            //同时设置提交执行多条SQL语句时,需要禁止自动提交,然后逐条预执行后一次性提交
            //可能存在SQL注入攻击风险
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            statement.executeUpdate(sq);
            int rows=statement.executeUpdate(sql);
            conn.commit();
//            statement.setString(1, book.getName());
//            statement.setDouble(2, book.getPrice());
//            statement.setString(3, book.getAuthor());
//            statement.setInt(4, book.getBookCount());


//            int rows=statement.executeUpdate();
//            int rows[]=statement.executeBatch();
            if (rows>0){
//            if (rows.length>0){
                out.print("成功添加了"+rows+"条数据!");
            }
        } else {
            out.print("数据库连接失败!");
        }


    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        out.print("添加数据失败!");
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }

            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
%>
</body>
</html>
