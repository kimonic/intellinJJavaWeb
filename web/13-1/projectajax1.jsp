<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/22
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax实例项目1</title>
    <script language="JavaScript" src="AjaxRequest.js"></script>
</head>
<body>
<%--下拉列表   此处的onchange事件执行时无效,原因不明--%>
<select name="province" id="province" onchange="getCity(this.value());"></select><br><br><br><br><br>

<select name="city" id="city">

</select>

<script language="JavaScript" charset="UTF-8">
    function getProvince() {
        var loader = new net.AjaxRequest("/ZoneServlet?action=getProvince", deal_getProvince, onError, "POST");
    }
    //js解决select标签中的onchange事件无效的问题
    document.getElementById("province").onchange=function () {
        var obj = document.getElementById("province"); //定位id,获取select对象
        var index = obj.selectedIndex; // 获取select对象中当前显示的索引即选中索引
        // var text = obj.options[index].text; // 选中文本与value值相同
        var value = obj.options[index].value; // 选中值
        getCity(value);
    }

    function deal_getProvince() {
        provinceArr = this.req.responseText.split(",");
        for (var i = 0; i < provinceArr.length; i++) {
            document.getElementById("province").options[i] =
                new Option(provinceArr[i], provinceArr[i]);
        }

        if (provinceArr[0]!=""){
            getCity(provinceArr[0]);
            document.getElementById("province").options[0].selected;
        }
    }
    window.onload=function (ev) {
        getProvince();
    }

    function getCity(selProvince) {
        // var encoder=new TextEncoder();
        // var s=encoder.encode(selProvince);
        //使用vencodeURI(selProvince)对中文字符进行utf-8编码,ie浏览器中,
        // 含中文的请求将会报异常,在ie浏览器中,不支持
        // var encoder=new TextEncoder();
        // var s=encoder.encode(selProvince);这种编码方式,可能是引发异常
        //程序代码在异常处终止执行
        var  loader=new net.AjaxRequest("/ZoneServlet?action=getCity&province="+encodeURI(selProvince),deal_getCity,onError,"POST");
    }
    function deal_getCity() {
        cityArr=this.req.responseText.split(",");
        document.getElementById("city").length=0;
        for (var i = 0; i < cityArr.length; i++) {
            document.getElementById("city").options[i]=
                new Option(cityArr[i],cityArr[i]);
        }
    }
    function onError() {
        alert("错误!");

    }

</script>


</body>
</html>
