<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/14
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%--7-3/dateindex.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>日期jsp页面</title>
    <style type="text/css">
        #clock {
            width: 420px;
            height: 80px;
            background-color: #EDEDED;
            font-size: 25px;
            font-weight: bold;
            border: solid 5px orange;
            padding: 20px;
        }

        #week {
            padding-top: 15px;
            color: #0080FF;
        }
    </style>
    <%--设置代码每间隔1秒刷新一次--%>
    <meta http-equiv="Refresh" content="1">
</head>
<body>
<%--scope为application时不会有刷新效果 page才会有--%>
<%--原因:calendar.getinstance()方法获取的单一对象是一个固定对象,不会即时刷新时间--%>
<%--要想获取当前时间,必须使用calendar.getinstance()每次进行重新获取--%>
<jsp:useBean id="date" class="S_7_3.DateBean" scope="application"/>
<div align="center">
    <div id="clock">
        <div id="time">
            <jsp:getProperty name="date" property="dateTime"/>
        </div>
        <div id="week">
            <jsp:getProperty name="date" property="week"/>
        </div>
    </div>
</div>

</body>
</html>
