<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/14
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8");%>
<%--src默认目录下引用的类不能实例化,必须是二级包名里面的类才可以--%>
<jsp:useBean id="person" class="jsppackage.Person" scope="page">
    <jsp:setProperty name="person" property="*"/>
</jsp:useBean>
<table align="center" width="400">
    <tr>
        <td>姓名:</td>
        <td>
            <jsp:getProperty name="person" property="name"/>
        </td>
    </tr>
    <tr>
        <td>性别:</td>
        <td>
            <jsp:getProperty name="person" property="sex"/>
        </td>
    </tr>
    <tr>
        <td>年龄:</td>
        <td>
            <jsp:getProperty name="person" property="age"/>
        </td>
    </tr>
    <tr>
        <td>地址:</td>
        <td>
            <jsp:getProperty name="person" property="address"/>
        </td>
    </tr>
</table>

</body>
</html>



















