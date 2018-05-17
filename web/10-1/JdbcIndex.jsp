<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/17
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%--/10-1/JdbcIndex.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据库连接测试</title>
</head>
<body>
<%
    Connection conn = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
//        jdbc:mysql://localhost:3306/test?serverTimezone=GMT 本句严格区分大小写
        String name = "root";
        String password = "dingzhixin";
        conn=DriverManager.getConnection(url, name, password);
        if (conn != null) {
            out.print("数据库连接成功!");
            conn.close();
        } else {
            out.print("数据库连接失败!");
        }

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

%>
</body>
</html>






















