<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--<%@ page isErrorPage="true" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/18
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String[] arr = {"亦筝笙", "明日科技", "土豆金服"};
    request.setAttribute("book", arr);
    String[] arr1 = (String[]) request.getAttribute("book");
    for (int i = 0; i < arr1.length; i++) {
        request.setAttribute("requestl", i);
%>
<%--必须将循环变量保存到request中,否则不能正确的输出数组内容--%>
${requestl}:${book[requestl]}<br>
<%--可以使用直接指定索引的方式访问数组中指定位置的元素--%>
<%--${book[1]}<br>--%>
<%
    }

    List<String> list = new ArrayList<>();
    list.add("苹果");
    list.add("牛奶");
    list.add("果冻");
    session.setAttribute("fruit", list);
    for (int i = 0; i < list.size(); i++) {
%>
<%--使用EL访问时,变量名即为request,session等保存时的变量名--%>
${fruit[0]}<br>
${fruit[1]}<br>
${fruit[2]}<br>

<%
    }
    request.setAttribute("user", "");
    request.setAttribute("user1", null);
%>
${"明日科技"}<br>
<%--判断是否为空或非空--%>
${empty user}<br>
${empty user1}<br>
${not empty user1}<br>

<%
    request.setAttribute("store",70);
%>
判断成绩:${store>60 and store<65}<br>
<%
    request.setAttribute("car","");

%>
<%--三目运算符--%>
car的值为:${empty car?"car 为空!":car}<br>
<%--EL内置对象--%>
<%--不可以通过pageContext对象获取保存到request对象中的变量--%>
\${pageContext.request.serverPort}:
${pageContext.request.serverPort}<br>
\${pageContext.response.contentType}:
${pageContext.response.contentType}<br>
\${pageContext.out.bufferSize}:
${pageContext.out.bufferSize}<br>
\${pageContext.session.maxInactiveInterval}:
${pageContext.session.maxInactiveInterval}<br>
<%--使用exception时需要指定该语句<%@ page isErrorPage="true" %>--%>
\${pageContext.exception.message}:
${pageContext.exception.message}<br>
<%--该句执行会直接崩溃--%>
\${pageContext.page.class}:
\${pageContext.page.class}<br>
<%--该行代码获取到的值为空--%>
\${pageContext.servletContext.contextPath}:
${pageContext.servletContext.contextPath}<br>

<jsp:useBean id="person" class="jsppackage.Person" scope="page" type="jsppackage.Person">
    <jsp:setProperty name="person" property="name" value="明日科技"/>
</jsp:useBean>
获取作用域内变量的值
<%--作用域名称.javabean的id.属性名--%>
${pageScope.person.name}<br>
requestScope作用域:
<%--作用域名称.设置的属性名[索引位置]--%>
${requestScope.book[0]}<br>
sessionScope作用域:
${sessionScope.fruit[0]}<br>
<%
    application.setAttribute("welcome","欢迎光临");
%>
applicationScope作用域:
${applicationScope.welcome}<br>
<form action="result.jsp" method="post">
    <input type="text" name="name"><br>
    <input type="submit" value="提交">
</form>

<form action="result.jsp" method="post">
    <input type="checkbox" name="affect" value="爬山">爬山
    <input type="checkbox" name="affect" value="游泳">游泳
    <input type="checkbox" name="affect" value="慢跑">慢跑
    <input type="checkbox" name="affect" value="跑步">跑步
    <input type="submit"  value="提交">
</form>
<
<form action="result.jsp" method="post" name="form1">
<textarea name="content" cols="30" rows="8"></textarea>
    <input type="submit"  value="提交"  name="button">
</form>



</body>
</html>
