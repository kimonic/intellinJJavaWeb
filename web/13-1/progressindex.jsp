<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/22
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件进度条页面</title>
    <style>
        .prog_border{
            height: 15px;
            width: 255px;
            background: #9ce0fd;
            border: 1px solid #FFFFFF;
            margin: 0;
            padding: 0;
            display: none;//不显示
            position: relative;
            left: 25px;
            float: left;//居左对齐
        }
    </style>
    <%--包含ajax的自定义js文件--%>
    <script language="JavaScript" src="AjaxRequest.js"></script>
    <script language="JavaScript">
        function deal(form) {
            form.submit();
            getProgress();
            timer=window.setInterval("getProgress()",1000);
        }
        
        
        function deal_p() {
            //在此处的函数中添加alert();函数可能需要重启服务器才会有效果
            var h=this.req.responseText;
            h=h.replace(/\s/g,"");//去除字符串中的unicode空白字符
            document.getElementById("progressBar").style.display="block";//显示进度条progressBar
            //此处的px不能少,否则没效果,直接使用.width时不需要加"px"否则没有效果
            document.getElementById("imgprogress").width=h*(255/100);//显示完成的进度
            // document.getElementById("imgprogress").style.width=h*(255/100)+"px";//显示完成的进度,另一种写法
            document.getElementById("progressPercent").style.display="";//显示百分比
            progressPercent.innerHTML=h+"%";

        }
        function onError() {
            alert("上传文件出错!");
        }

        // new Date().getTime(),url链接不加该代码容易造成不更新的情况
        function getProgress() {
            var loader=new net.AjaxRequest("showprogress.jsp?nocache="+new Date().getTime(),deal_p,onError,"POST");
        }

    </script>
</head>
<body>

<%--文件上传需要设置enctype="multipart/form-data"属性--%>
<form action="/UploadServlet1" name="form1" method="post" enctype="multipart/form-data">
    请选择上传的文件:<input name="file" id="file" type="file" size="42">
    <img src="http://img.zcool.cn/community/010e3e5540ded60000017c94d2beeb.jpg" width="61" height="23" onclick="deal(form1);">
    <img src="http://img.zcool.cn/community/01b03a59688086a8012193a3a3c4d5.gif" width="61" height="23" onclick="form1.reset();">
</form>

<div id="progressBar" class="prog_border" align="left" >
    <%--需要设置style后js代码才会生效??--%>
    <img src="" width="0" height="13" id="imgprogress" style="background: #0080FF" />
</div><span id="progressPercent" style="width: 40px;display: none">0%</span>
</body>


</html>
