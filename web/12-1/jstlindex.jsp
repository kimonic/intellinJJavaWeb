<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/18
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>jstl测试</title>
</head>
<body>
escapeXml为true时:
<c:out value="水平线标记<hr>" escapeXml="true"/><br>
escapeXml为false时:
<c:out value="水平线标记<hr>" escapeXml="false"/><br>

</body>
</html>
