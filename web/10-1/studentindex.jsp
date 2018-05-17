<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/17
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量添加学生数据</title>
</head>
<body>
<jsp:useBean id="bench" class="S_10_1.Bench"/>
<%
    int row=bench.saveBench();
%>
</body>
</html>
