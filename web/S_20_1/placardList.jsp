<%--placardList.jsp  公告列表页面--%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//    输出访问的本地地址http://localhost:8080/
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//    out.print("basePath的路径是:"+basePath);
    out.print("basePath的路径是1:"+request.getAttribute("description"));
    out.print("<br>basePath的路径是2:"+request.getAttribute("createTime"));
    out.print("<br>basePath的路径是3:"+request.getAttribute("rssContent"));
//    out.print("basePath的路径是2:"+basePath);
//    out.print("basePath的路径是3:"+basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>公告列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" type="text/css" href="style.css">
    <style type="text/css">
        <!--
        .style1 {
            color: #FFFFFF
        }

        -->
    </style>
</head>

<body>
<table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td width="415">&nbsp;???</td>
        <td width="43">&nbsp;???</td>
        <td width="42">&nbsp;???</td>
    </tr>
    <tr>
        <td height="27" colspan="3" align="center" bgcolor="#FFFFFF"><span class="style1">???</span><span
                class="style1">????</span>[${description}]${createTime}????</td>
    </tr>
    <tr>
        <td height="27" align="center" bgcolor="#FFFFFF">标题</td>
        <td align="center" bgcolor="#FFFFFF">修改</td>
        <td align="center" bgcolor="#FFFFFF">删除</td>
    </tr>
    <c:if test="${rssContent==null}">
        <tr>
            <td height="27" colspan="3" align="center" bgcolor="#FFFFFF">暂无公告！</td>
        </tr>
    </c:if>
    <c:forEach var="form" items="${rssContent}">
        <tr>
            <td height="27" bgcolor="#FFFFFF">&nbsp;${form.title}</td>
            <td align="center" bgcolor="#FFFFFF"><a href="ManagerXmlServlet?action=modify_query&id=${form.id}"><img
                    src="images/modify.gif" width="20" height="18" border="0"></a></td>
            <td align="center" bgcolor="#FFFFFF"><a href="ManagerXmlServlet?action=del&id=${form.id}"><img
                    src="images/del.gif" width="23" height="22" border="0"></a></td>
        </tr>
    </c:forEach>
</table>
<c:if test="${rssContent!=null}">
    <table width="500" height="42" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center"><a href="ManagerXmlServlet?action=clearAll">[删除全部公告]</a></td>
        </tr>
    </table>
</c:if>

</body>
</html>
