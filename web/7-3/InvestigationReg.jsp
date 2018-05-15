<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/15
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%--7-3/InvestigationReg.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表单提交结果</title>
</head>
<body>
<jsp:useBean id="papers" class="S_7_3.Paper"/>
<jsp:useBean id="convert" class="S_7_3.Convert"/>
<%--注意javabean中私有变量名称要与表单中的name属性变量名称一致--%>
<jsp:setProperty name="papers" property="*"/>
<div>
    <h1>调查结果</h1>
    <ul>
        <li>你经常使用的编程语言:<%=convert.arr2Str(papers.getLanguages())%></li>
        <li>你目前掌握的技术:<%=convert.arr2Str(papers.getTechnics())%></li>
        <li>在学习中感觉困难的部分:<%=convert.arr2Str(papers.getParts())%></li>
    </ul>
</div>
</body>
</html>
