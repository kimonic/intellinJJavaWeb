<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/18
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="/stringDeal"   prefix="yy"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL测试请求信息传递</title>
</head>
<body>
<%--当指定参数不存在时返回空字符串,不返回null--%>
<%--获取单参数--%>
${param.name}<br>
<%--获取数组型参数--%>
${paramValues.affect[0]}<br>
${paramValues.affect[1]}<br>
${paramValues.affect[2]}<br>
${paramValues.affect[3]}<br>
${paramValues.affect[4]}<br>
<%--header查看链接方式--%>
${header.connection}<br>
<%--测试该语句崩溃--%>
\${header[user-agent]}<br>
web.xml中的初始化参数author的值是:
${initParam.author}<br>
<%
    Cookie cookie=new Cookie("username","kimonik");
    response.addCookie(cookie);
%>
cookie中保存的username的参数值是:
${cookie.username.value}<br>

传递的结果内容为:<br>
${yy:shiftEnter(param.content)}


</body>
</html>
