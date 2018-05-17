<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/17
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%--/10-1/BookIndex.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>保存图书信息页面</title>
</head>
<body>

<form action="AddBook.jsp" method="post" onsubmit="return check(this);">
    <table align="center" width="450">
        <tr>
            <td align="center" colspan="2"><h2>添加图书信息</h2>
                <hr>
            </td>
        </tr>
        <tr>
            <td align="right">图书名称:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td align="right">价格:</td>
            <td><input type="text" name="price"></td>
        </tr>
        <tr>
            <td align="right">数量:</td>
            <td><input type="text" name="bookCount"></td>
        </tr>
        <tr>
            <td align="right">作者:</td>
            <td><input type="text" name="author"></td>
        </tr>
        <tr>
            <td align="center" colspan="2"><input type="submit" value="添加"></td>
        </tr>
    </table>
</form>

</body>
</html>




























