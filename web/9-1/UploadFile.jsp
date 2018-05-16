<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/16
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%--/9-1/UploadFile.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<form action="/uploadServlet" enctype="multipart/form-data" method="post">
    选择文件&nbsp;&nbsp;&nbsp;&nbsp;<input type="file" name="file1" id="file1"><br>
    <input type="submit" value="上传" name="upload">
</form>
</body>
</html>
