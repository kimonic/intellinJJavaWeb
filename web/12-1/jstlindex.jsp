<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/18
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>jstl测试</title>
</head>
<body>
escapeXml为true时:
<%--显示所有文本--%>
<c:out value="水平线标记<hr>" escapeXml="true"/><br>
escapeXml为false时:
<%--显示HTML语言标记后的文本--%>
<c:out value="水平线标记<hr>" escapeXml="false"/><br>
<ul>
    <li>定义request范围内的变量username</li><br>
    <c:set value="明日科技" var="username" scope="request"/>
    <c:out value="username的值为:${username}"/><br>
    <li>设置news对象的title属性</li><br>
    <jsp:useBean id="news" class="S_7_3.News" />
    <c:set target="${news}" property="title">这是新闻标题</c:set>
    <c:out value="标题:${news.title}" /><br>
    <c:remove var="news" scope="page"/>
    title的值为:<%=news.getTitle()!=null?news.getTitle():"值为空!"%><br>
    <%--c:remove只能移除由c:set设置的变量值,移除由jsp:usebean设置的对象时无效
    原因??一个是定义的变量,一个是定义的对象
    --%>
    <c:remove var="username" scope="request"/>
    username的值为:<c:out value="${username}" default="值为空"/><br>

    <%--捕获遗产标签--%>
<c:catch var="error">
    <jsp:useBean id="news1" class="S_7_3.News" scope="request"/>
    <c:set target="${news1}" property="pwd" >123456</c:set>
</c:catch>
    <%--捕获标签内无法输出异常信息,需在外面输出--%>
    <c:out value="${error}" />

</ul>

<h2>应用c:import标签导入网站banner</h2>
<c:set var="typeName" value="流行金曲|经典老歌|热舞DJ|欧美金曲|少儿歌曲|轻音乐|最新上榜"/>
<c:import url="navigation.jsp" charEncoding="UTF-8">
    <c:param name="typeList" value="${typeName}"/>
</c:import>
<h2>通过c:url标签生成带参数的URL地址</h2>
<%--使用该方式生成url时,空格将会被转化为+号--%>
<c:url var="path" value="register.jsp" scope="page">
    <c:param name="user" value="kim o nik"/>
    <c:param name="email" value="2031382182@qq.com"/>
</c:url>
<a href="${pageScope.path}">提交注册</a>

<%--<h2>使用重定向标签</h2>--%>
<%--<c:redirect url="/FindServlet">--%>
    <%--&lt;%&ndash;使用该标签为参数赋值,在响应页面使用${param.action}获取传递的值&ndash;%&gt;--%>
    <%--<c:param name="action" value="query"/>--%>
<%--</c:redirect>--%>

<h2>根据是否登陆显示不同的内容</h2>
<c:if test="${empty param.username}" var="result">
    <form action="" method="post">
        用户名:<input type="text" name="username" id="username"><br><br>
        <input type="submit" name="submit" value="登陆">
    </form>
</c:if>
<c:if test="${!result}">
    [${param.username}]欢迎访问我公司网站!<br>
</c:if>
<h2>根据是否登陆显示不同的内容</h2>
<%--c:choose标签至少应该含有一个c:when标签,c:otherwise标签可有可无,没有且--%>
<%--无符合条件时,c:choose标签不会被处理--%>
<c:choose>
    <%--符合条件时执行  c:when标签必须出现在c:otherwise标签之前--%>
    <c:when test="${empty param.username}">
        <%--<form action="" method="post">--%>
            <%--用户名:<input type="text" name="username" id="username"><br><br>--%>
            <%--<input type="submit" name="submit" value="登陆">--%>
        <%--</form>--%>
        请输入用户名!<br>
    </c:when>
    <%--无符合条件时默认执行--%>
    <c:otherwise>
        [${param.username}]欢迎访问我公司网站!<br>
    </c:otherwise>
</c:choose>
<h2>c:choose标签综合运用</h2>
<c:set var="hours">
    <%=new Date().getHours()%>
</c:set>
<c:set var="second">
    <%=new Date().getMinutes()%>
</c:set>
<c:choose>
    <c:when test="${hours>1&&hours<6}">早上好!</c:when>
    <c:when test="${hours>6&&hours<11}">中午好!</c:when>
    <c:when test="${hours>11&&hours<17}">下午好!</c:when>
    <c:when test="${hours>17&&hours<24}">晚上好!</c:when>
    <c:otherwise>真不巧,现在刚好是某一个时间点!</c:otherwise>
</c:choose>
现在的时间是:${hours}时${second}分<br>
<h2>循环遍历测试</h2>
<%
    List<String> list=new ArrayList<>();
    for (int i = 0; i <10 ; i++) {
        list.add("这是第"+(i+1)+"条信息!");
    }
    request.setAttribute("list",list);
%>
<b>遍历lsit集合的所有元素!</b><br>
<c:forEach items="${requestScope.list}" var="keyword" varStatus="id">
    ${id.index}&nbsp;${keyword}<br>
</c:forEach>
<h2>遍历list集合中第一个元素之后的元素,不包含第一个</h2>

<c:forEach items="${requestScope.list}" var="keyword" varStatus="id" begin="1" end="6">
    <%--var--循环的对象值--%>
    <%--varStatus--循环的状态变量--%>
    <%--begin--开始索引位置--%>
    <%--end--结束索引位置--%>
    <%--items--对象的容器,集合等--%>
    ${id.index}&nbsp;${keyword}<br>
</c:forEach>
<h2>使用c:foreach列举10以内的全部奇数</h2>
<c:forEach begin="1" end="10" step="2" var="i">
    &nbsp;&nbsp;${i}<br>
</c:forEach>

<h2>利用c:fortokens分隔字符串</h2>
<c:set var="sourseStr" value="java,javascript,php,html5,servlet"/>
<b>原字符串:${sourseStr}</b><br>
<b>分隔后的字符串:</b><br>
<c:forTokens items="${sourseStr}" delims="," var="index" >
    ${index}<br>
</c:forTokens>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</body>
</html>
