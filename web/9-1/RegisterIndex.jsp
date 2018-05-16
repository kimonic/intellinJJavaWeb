<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/16
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%--/9-1/RegisterIndex.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<form action="/SaveServlet" method="post" onsubmit="return reg(this);">
    <table align="center" border="0" width="500">
        <tr>
            <td align="right" width="30%">用户名:</td>
            <td><input type="text" name="username" class="box"></td>
        </tr>
        <tr>
            <td align="right" width="30%">密码:</td>
            <td><input type="password" name="password" class="box"></td>
        </tr>
        <tr>
            <td align="right" width="30%">确认密码:</td>
            <td><input type="password" name="repassword" class="box"></td>
        </tr>
        <tr>
            <td align="right" width="30%">性别:</td>
            <td>
                <input type="radio" name="sex" value="男" class="box" checked="checked">男
                <input type="radio" name="sex" value="女" class="box">女
            </td>
        </tr>
        <tr>
            <td align="right" width="30%">密码找回问题:</td>
            <td><input type="text" name="question" class="box"></td>
        </tr>
        <tr>
            <td align="right" width="30%">密码找回答案:</td>
            <td><input type="text" name="answer" class="box"></td>
        </tr>
        <tr>
            <td align="right" width="30%">邮箱:</td>
            <td><input type="text" name="email" class="box"></td>
        </tr>

        <tr>
           <td colspan="2" align="center" height="40">
               <input type="submit" value="注册">
               <input type="reset" value="重置">
           </td>
        </tr>
    </table>

</form>
</body>
</html>
