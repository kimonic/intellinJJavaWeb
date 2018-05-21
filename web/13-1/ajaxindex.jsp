<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/21
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax实例应用</title>
    <%--该style对应相应使用者的id--%>
    <style type="text/css">
        #toolTip {
            /*绝对坐标位置*/
            position: absolute;
            /*外边距*/
            left: 331px;
            top: 390px;
            /*宽高*/
            width: 198px;
            height: 148px;
            /*内边距*/
            padding-top: 45px;
            padding-left: 25px;
            padding-right: 25px;
            z-index: 1;
            display: none;
            /*文本颜色*/
            color: red;
            /*背景色,背景图片,只有一个起作用*/
            background-color: #0080FF;
            /*background-image: url("../images/toolTip.jpg");*/
        }
    </style>
</head>
<body>
<form action="" name="form1" method="post">
    用户名:<input type="text" name="username" id="username" size="32">
    <%--<img src="../images/toolTip.jpg" alt="" width="104" height="23" style="cursor:--%>
    <%--hand;" onclick="checkUser(form1.username);">--%>
    <input type="button" value="检查用户名" onclick=" getInfo(form1.username);">
    <br><br>
    密&nbsp;&nbsp;码:<input type="password" name="password" id="password" size="35"><br><br>
    密&nbsp;&nbsp;码:<input type="password" name="repassword" id="repassword" size="35"><br><br>
    Email:<input type="text" name="email" id="email" size="45"><br><br>
    <%--<input type="image" name="imageField" src="../images/toolTip.jpg">--%>

</form>
<br><br><br><br><br><br><br>
<div id="toolTip"></div>


<%--<script language="JavaScript">--%>
<%--// 创建兼容的httprequest--%>
<%--function createRequest(url) {--%>
<%--http_request = false;--%>
<%--if (window.XMLHttpRequest) {--%>
<%--http_request = new XMLHttpRequest();--%>
<%--} else if (window.ActiveXObject) {--%>
<%--try {--%>
<%--http_request = new ActiveXObject("Msxml2.XMLHTTP");--%>
<%--} catch (e) {--%>
<%--try {--%>
<%--http_request = new ActiveXObject("Microsoft.XMLHTTP");--%>
<%--} catch (e) {--%>

<%--}--%>
<%--}--%>
<%--}--%>
<%--if (!http_request) {--%>
<%--alert("不能创建httprequest对象实例");--%>
<%--return false;--%>
<%--}--%>
<%--//处理响应,此处调用的处理函数不能带有()和参数--%>
<%--//要调用带参数的函数使用 function(){getResult(parameter)}方式--%>
<%--http_request.onreadystatechange = getResult;--%>
<%--http_request.open("POST", url, true);--%>
<%--http_request.send(null);--%>
<%--}--%>

<%--function getResult() {--%>
<%--if (http_request.readyState == 4) {--%>
<%--if (http_request.status == 200) {--%>
<%--document.getElementById("toolTip").innerHTML = http_request.responseText;--%>
<%--document.getElementById("toolTip").style.display = "block";--%>
<%--} else {--%>
<%--alert("您所请求的页面有错误!");--%>
<%--}--%>
<%--}--%>
<%--}--%>

<%--function checkUser(username) {--%>
<%--if (username.value == "") {--%>
<%--alert("请输入用户名!");--%>
<%--} else {--%>
<%--createRequest('checkUser.jsp?user=' + username.value);--%>
<%--}--%>
<%--}--%>
<%--</script>--%>
<%--script标签调用外部js文件时,其内部不能再继续写js函数,否则调用无效--%>
<%--应该另外再用一个script标签重新写新函数--%>
<script language="JavaScript" src="AjaxRequest.js"></script>
<script language="JavaScript">
    function onError() {
        alert("您的操作有误??????!");
    }

    function getInfo(username) {
        if (username.value === "") {
            alert("请输入用户名!");
        } else {
            //该构造函数内传递的方法都不能带(),否则会先执行一次传递的方法,使用onError()
            //时会先显示一次错误弹窗
            //当函数名后加上括号时，通常会执行函数体本身。如果函数有返回值时，此时会得到函数的返回值；
            //当函数名后不加括号时，其实质上是一个函数指针，只是用于找到函数体的位置，不会直接执行函数体；
            // 因此，如果时把函数作为参数进行值传递时，通常不需要加括号，只是把它当做一个函数指针；
            // 但是如果要进行函数调用的时候，
            // 由于是想要得到函数的返回值，因此必须要加上括号。
            var loader = new net.AjaxRequest('checkUser.jsp?user=' + username.value,
                deal_getinfo, onError, "POST");

        }
    }

    function deal_getinfo() {
        document.getElementById("toolTip").innerHTML = this.req.responseText;
        //没有该句时不会显示内容
        document.getElementById("toolTip").style.display = "block";
    }
</script>
</body>
</html>
