<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/21
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>检测用户名页面</title>
</head>
<body>
<%
    String[] userList={"明日科技","mr","mrsoft","wgh"};
    String user=request.getParameter("user");
    Arrays.sort(userList);
    int result=Arrays.binarySearch(userList,user);
    if (result>-1){
        out.print("很抱歉,该用户名已被注册!");
    }else {
        out.print("恭喜你,该用户名没有被注册!");
    }



%>
</body>
</html>
