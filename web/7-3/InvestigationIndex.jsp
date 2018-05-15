<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/15
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%--7-3/InvestigationIndex.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>调查问卷</title>
</head>
<body>
<div align="center">
    <div align="left" style="width: max-content">
        <form action="InvestigationReg.jsp" method="post">
            <div>
                <h1>问卷调查</h1>
                <hr>
                <ul>
                    <li>你经常用那些编程语言进行开发?</li>
                    <li>
                        <input type="checkbox" name="languages" value="JAVA">JAVA
                        <input type="checkbox" name="languages" value="PHP">PHP
                        <input type="checkbox" name="languages" value=".NET">.NET
                        <input type="checkbox" name="languages" value="VC++">VC++
                    </li>
                </ul>
                <ul>
                    <li>你目前所掌握的技术:</li>
                    <li>
                        <input type="checkbox" name="technics" value="HTML">HTML
                        <input type="checkbox" name="technics" value="JAVA BEAN">JAVA BEAN
                        <input type="checkbox" name="technics" value="SEVERLET">SEVERLET
                        <input type="checkbox" name="technics" value="JSP">JSP
                    </li>
                </ul>
                <ul>
                    <li>在学习中那一部分感觉有困难?</li>
                    <li>
                        <input type="checkbox" name="parts" value="JSP">JSP
                        <input type="checkbox" name="parts" value="STRUTS">STRUTS
                    </li>
                </ul>
                <input type="submit" value="提交">
            </div>


        </form>
    </div>
</div>
</body>
</html>
