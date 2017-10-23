<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href=".<%=basePath %>/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../../../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>我的桌面</title>
</head>
<body>
<div class="page-container">
    <p class="f-20 text-success">欢迎登录得实产品管理系统！</p>
    <p>登录IP地址：${ip}</p>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th colspan="4" scope="col">信息更新统计</th>
        </tr>
        <tr class="text-c">
            <th>统计</th>
            <th>管理员</th>
            <th>产品库</th>
            <th>资源库</th>
        </tr>
        </thead>
        <tbody>
        <tr class="text-c">
            <td>总数</td>
            <td>{$data.user.all}</td>
            <td>{$data.goods.all}</td>
            <td>{$data.res.all}</td>
        </tr>
        <tr class="text-c">
            <td>今日</td>
            <td>{$data.user.today}</td>
            <td>{$data.goods.today}</td>
            <td>{$data.res.today}</td>
        </tr>
        <tr class="text-c">
            <td>昨日</td>
            <td>{$data.user.yesterday}</td>
            <td>{$data.goods.yesterday}</td>
            <td>{$data.res.yesterday}</td>
        </tr>
        <tr class="text-c">
            <td>本周</td>
            <td>{$data.user.week}</td>
            <td>{$data.goods.week}</td>
            <td>{$data.res.week}</td>
        </tr>
        <tr class="text-c">
            <td>本月</td>
            <td>{$data.user.month}</td>
            <td>{$data.goods.month}</td>
            <td>{$data.res.month}</td>
        </tr>
        </tbody>
    </table>
   <!-- <table class="table table-border table-bordered table-bg mt-20">
        <thead>
        <tr>
            <th colspan="2" scope="col">服务器信息</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="30%">服务器计算机名</th>
            <td><span id="lbServerName">http://127.0.0.1/</span></td>
        </tr>
        <tr>
            <td>服务器IP地址</td>
            <td>192.168.1.1</td>
        </tr>
        <tr>
            <td>服务器域名</td>
            <td>www.h-ui.net</td>
        </tr>
        <tr>
            <td>服务器端口 </td>
            <td>80</td>
        </tr>
        <tr>
            <td>服务器IIS版本 </td>
            <td>Microsoft-IIS/6.0</td>
        </tr>
        <tr>
            <td>本文件所在文件夹 </td>
            <td>D:\WebSite\HanXiPuTai.com\XinYiCMS.Web\</td>
        </tr>
        <tr>
            <td>服务器操作系统 </td>
            <td>Microsoft Windows NT 5.2.3790 Service Pack 2</td>
        </tr>
        <tr>
            <td>系统所在文件夹 </td>
            <td>C:\WINDOWS\system32</td>
        </tr>
        <tr>
            <td>服务器脚本超时时间 </td>
            <td>30000秒</td>
        </tr>
        <tr>
            <td>服务器的语言种类 </td>
            <td>Chinese (People's Republic of China)</td>
        </tr>
        <tr>
            <td>.NET Framework 版本 </td>
            <td>2.050727.3655</td>
        </tr>
        <tr>
            <td>服务器当前时间 </td>
            <td>2014-6-14 12:06:23</td>
        </tr>
        <tr>
            <td>服务器IE版本 </td>
            <td>6.0000</td>
        </tr>
        <tr>
            <td>服务器上次启动到现在已运行 </td>
            <td>7210分钟</td>
        </tr>
        <tr>
            <td>逻辑驱动器 </td>
            <td>C:\D:\</td>
        </tr>
        <tr>
            <td>CPU 总数 </td>
            <td>4</td>
        </tr>
        <tr>
            <td>CPU 类型 </td>
            <td>x86 Family 6 Model 42 Stepping 1, GenuineIntel</td>
        </tr>
        <tr>
            <td>虚拟内存 </td>
            <td>52480M</td>
        </tr>
        <tr>
            <td>当前程序占用内存 </td>
            <td>3.29M</td>
        </tr>
        <tr>
            <td>Asp.net所占内存 </td>
            <td>51.46M</td>
        </tr>
        <tr>
            <td>当前Session数量 </td>
            <td>8</td>
        </tr>
        <tr>
            <td>当前SessionID </td>
            <td>gznhpwmp34004345jz2q3l45</td>
        </tr>
        <tr>
            <td>当前系统用户名 </td>
            <td>NETWORK SERVICE</td>
        </tr>
        </tbody>
    </table>-->
</div>
<!--<footer class="footer mt-20">
    <div class="container">

    </div>
</footer>-->
<script type="text/javascript" src="<%=basePath %>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui/js/H-ui.min.js"></script>
<script>
</script>
</body>
</html>