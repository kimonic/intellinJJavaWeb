<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/14
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>7.3练习实例</title>
</head>
<body>
<form action="realease.jsp" method="post">
    <table align="center"  width="450" height="260" border="1">
        <tr>
            <td align="center" colspan="2" height="40"><b>新闻发布</b></td>
        </tr>
        <tr>
            <td  align="right">标题:</td>
            <td><input type="text" name="title" size="30" ></td>
        </tr>
        <tr>
            <td align="right">内容:</td>
            <td><textarea rows="8" name="content" cols="40"></textarea></td>
        </tr>
        <tr>
            <td align="center" colspan="2"><input type="submit" value="发布" name="submit"> </td>
        </tr>
    </table>
</form>
</body>
</html>
