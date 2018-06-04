<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/4
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dom4j使用实例</title>
</head>
<body>
<form name="form1" method="post" action="/SaveXmlServlet" target="_blank" >
    公告标题:<input type="text" name="title" id="title" size="52"><br><br><br>
    <%--textarea必须以<textarea></textarea>方式出现,否则可能包含显示下面的代码--%>
    公告内容:<textarea id="content" name="content" rows="10" cols="50"></textarea><br><br><br>
    <input type="submit" value="提交" name="submit" class="btn_grey">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="reset" value="重置" name="reset" class="btn_grey"><br>

</form>

<c:redirect url="/ManagerXmlServlet">
    <c:param name="action" value="query"/>
</c:redirect>
</body>
</html>
