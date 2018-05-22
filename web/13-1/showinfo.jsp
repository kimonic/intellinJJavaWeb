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
    <%--//window.load方法需要单独放到header内才会执行???--%>
    <script language="JavaScript">
        window.onload=function () {
            getInfo();
            // 按照指定的周期（以毫秒计）来调用函数或计算表达式。
            window.setInterval("getInfo()","5000");//每隔5s调用一次getinfo()方法
        }
    </script>
</head>
<body>
<div >
    <marquee style="border: 1px solid;height: 50px;width: 200px;padding: 5px" behavior="" direction="up" scrollamount="3">
        <div id="showinfo"></div>
    </marquee>
</div>
<script language="JavaScript" src="AjaxRequest.js"></script>
<script language="JavaScript">
    var count=0;
    function onError() {
        alert("您输入的信息有误!");
    }
    
    function getInfo() {
        //需要new关键字
        count++;
        var  loader=new net.AjaxRequest("getinfo.jsp?count="+count,onload,onError,"POST");
    }

    function onload() {
        document.getElementById("showinfo").innerHTML=this.req.responseText;
    }
</script>
</body>
</html>
