<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/22
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
    margin: 0px;
    font-size: 12px;
    color: #938C43;
    line-height: 150%;
    text-align:center;
    }
    a:link{color: #9D943A;font-size:12px;}
    a:hover{color: #FF3300;font-size:12px;}
    a:visited{color: #9D943A;font-size:12px;}
    a.red:link{color: #ff0000;font-size:12px;}
    a.red:hover{color: #ff0000;font-size:12px;}
    a.red:visited{color: #ff0000;font-size:12px;}
    #marqueeBox{background:#f7f7f7;border:1px solid silver;padding:1px;text-align:center;margin:0 auto;}
    -->
    </style>
</head>
<body>
<h4>滚动新闻</h4>

<script language="JavaScript" type="text/javascript">
    var marqueeContent=new Array();
    marqueeContent[0]="<a href=http://xyq.163.com/news/2006/11/2-2-20061102170913.html target=_blank>用快速取回帐号密码</a>";
    marqueeContent[1]="<a href=http://ekey.163.com/ target=_blank>网易将军令官方网站</a>";
    marqueeContent[2]="<a href=http://xyq.163.com/download/wallpaper.htm target=_blank>最新壁纸下载</a>";
    marqueeContent[3]="<a href=http://xyq.163.com/download/around.htm target=_blank>最新屏保下载</a>";
    var marqueeInterval=new Array();
    var marqueeId=0;
    var marqueeDelay=2000;
    var marqueeHeight=20;
    function initMarquee() {
        var str=marqueeContent[0];
        document.write('<div id="marqueeBox" style="overflow:hidden;width:250px;height:'
            +marqueeHeight+'px" onmouseover="clearInterval(marqueeInterval[0])" ' +
            'onmouseout="marqueeInterval[0]=setInterval(\'startMarquee()\',marqueeDelay)">' +
            '<div>'+str+'</div></div>');
        marqueeId++;
        marqueeInterval[0]=setInterval("startMarquee()",marqueeDelay);
    }
    function startMarquee() {
        var str=marqueeContent[marqueeId];
        marqueeId++;
        if(marqueeId>=marqueeContent.length) marqueeId=0;
        if(document.getElementById("marqueeBox").childNodes.length==1) {
            var nextLine=document.createElement('DIV');
            nextLine.innerHTML=str;
            document.getElementById("marqueeBox").appendChild(nextLine);
        }
        else {
            document.getElementById("marqueeBox").childNodes[0].innerHTML=str;
            document.getElementById("marqueeBox").appendChild(document.getElementById("marqueeBox")
                .childNodes[0]);
            document.getElementById("marqueeBox").scrollTop=0;
        }
        clearInterval(marqueeInterval[1]);
        marqueeInterval[1]=setInterval("scrollMarquee()",20);
    }
    function scrollMarquee() {
        document.getElementById("marqueeBox").scrollTop++;
        if(document.getElementById("marqueeBox").scrollTop%marqueeHeight==(marqueeHeight-1)){
            clearInterval(marqueeInterval[1]);
        }
    }
    initMarquee();
</script>

</body>
</html>

