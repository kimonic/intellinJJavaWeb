<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/14
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="news" class="S_7_3.News"/>
<jsp:useBean id="encoding" class="S_7_3.CharactorEncoding"/>
<jsp:setProperty name="news" property="*"/>
<div align="center">
    <div id="container">
        <div id="title">
            <%--变量名为jsp:useBean中的id名称--%>
            <%=encoding.toString(news.getTitle())%>
        </div>
        <hr>
        <div id="content">
            <%=encoding.toString(news.getContent())%>
        </div>
    </div>
</div>
</body>
</html>














