<%@ page import="java.util.List" %>
<%@ page import="S_10_1.Book" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/17
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询图书信息页面</title>
</head>
<body>
<table align="center" width="450" border="1">
    <tr>
        <td align="center" colspan="7">
            <h2>所有图书信息</h2>
        </td>
    </tr>
    <tr align="center">
        <td><b>ID</b></td>
        <td><b>图书名称</b></td>
        <td><b>作者</b></td>
        <td><b>价格</b></td>
        <td><b>数量</b></td>
        <td><b>修改数量</b></td>
        <td><b>删除</b></td>
    </tr>
    <%
        List<Book> list = (List<Book>) request.getAttribute("list");
        if (list == null || list.size() == 0) {
            out.print("没有数据!");
        } else {
            for (Book book : list) {
    %>
    <tr style="align-items: center">
        <td align="center"><%=book.getId()%></td>
        <td align="center"><%=book.getName()%></td>
        <td align="center"><%=book.getAuthor()%></td>
        <td align="center"><%=book.getPrice()%></td>
        <td align="center"><%=book.getBookCount()%></td>
        <td align="center">
        <%--此处表单不能居中的原因是marginbottom有默认值--%>
               <form action="/UpdateServlet"  method="post" onsubmit="return check(this)"
                     style="margin: 10px;"               >
                   <input type="hidden" value="<%=book.getId()%>" name="id">
                   <input type="text" name="bookCount" size="3">
                   <input type="submit" value="修改">
               </form>
        </td>
        <td align="center">
            <a href="/DeleteServlet?id=<%=book.getId()%>">删除</a>
        </td>
    </tr>
    <%
            }
        }

    %>
</table>
</body>
</html>
