<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/15
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%--/9-1/ShowUser.jsp--%>
<%@ page import="java.util.*" %>
<%@ page import="S_9_1.UserInfoList" %>
<%@ page import="S_9_1.UserInfoTrace" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户在线统计系统</title>
</head>
<body>
<%
    UserInfoList list = UserInfoList.getInstance();
    UserInfoTrace trace = new UserInfoTrace();
    String name = request.getParameter("user");
    trace.setUser(name);
    session.setAttribute("list", trace);
    list.addUserinfo(trace.getUser());
    session.setMaxInactiveInterval(50);
%>
<textarea rows="8" cols="20">
<%
    Vector<String> vector = list.getList();
    if (vector != null && vector.size() > 0) {
        for (int i = 0; i < vector.size(); i++) {
            out.print(vector.elementAt(i));
        }
    }
%>
    </textarea>
</body>
</html>
