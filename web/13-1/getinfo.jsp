<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/21
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取公告信息(模拟)</title>
</head>
<body>
<%
    for (int i = 1; i <10 ; i++) {
        out.print("<li>"+"这是第"+i+"条信息</li>");
    }
%>
</body>
</html>
