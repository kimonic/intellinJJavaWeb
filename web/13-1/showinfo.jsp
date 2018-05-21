<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/21
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实时显示公告信息</title>
</head>
<body>
<div style="border: 1px solid;height: 50px;width: 200px;padding: 5px">
    <marquee behavior="" direction="up" scrollamount="3">
        <div id="showinfo"></div>
    </marquee>
</div>
<script language="JavaScript" src="AjaxRequest.js"></script>
<script language="JavaScript">
    window.onload=function () {
        getInfo();
        document.write("执行了");
        window.setInterval("getInfo()","10");//每隔10s调用一次getinfo()方法
    }
    function onError() {
        alert("您输入的信息有误!");
    }
    
    function getInfo() {
        var  loader=net.AjaxRequest("getinfo.jsp",onload,onError,"POST");
    }

    function onload() {
        document.getElementById("showinfo").innerHTML=this.req.responseText;
    }
</script>
</body>
</html>
