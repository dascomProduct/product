<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../../../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../../../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="../../../lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../../../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>用户查看</title>
</head>
<body>
<div class="cl pd-20" style=" background-color:#5bacb6">
    <img class="avatar size-XL l" src="../../../static/h-ui/images/ucnter/avatar-default.jpg">
    <dl style="margin-left:80px; color:#fff">
        <dt>
            <span class="f-18">{$data.username}</span>
        </dt>
        <dd class="pt-10 f-12" style="margin-left:0">{$data.remark|default="这家伙很懒，什么也没留下"}</dd>
    </dl>
</div>
<div class="pd-20">
    <form action="" method="post">
    <table class="table">
        <tbody>
        <tr>
            <th class="text-r">用户名：</th>
            <input type="hidden" name="id" value="{$data.id}">
            <td><input type="text" class="input-text" style="width: 50%" value="{$data.username}" placeholder="" id="name" name="name"></td>
        </tr>
        <tr>
            <th class="text-r" width="80">性别：</th>
            <td>
                <div class="radio-box">
                    <input name="sex" type="radio" value="1" id="sex-1" checked>
                    <label for="sex-1">男</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="sex-2" value="2" name="sex" >
                    <label for="sex-2">女</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="sex-3" value="3" name="sex" >
                    <label for="sex-3">保密</label>
                </div>
            </td>
        </tr>
        <tr>
            <th class="text-r">手机：</th>
            <td><input type="text" class="input-text" style="width: 50%" value="{$data.phone}" placeholder="" id="mobile" name="phone"></td>
        </tr>
        <tr>
            <th class="text-r">邮箱：</th>
            <td><input type="text" class="input-text" style="width: 50%" value="{$data.email}" placeholder="@" name="email" id="email"></td>
        </tr>
        <tr>
            <th class="text-r">备注：</th>
            <td>
                <textarea name="beizhu" cols="" rows="" class="textarea" style="width: 50%" placeholder="说点什么...最少输入10个字符" onKeyUp="$.Huitextarealength(this,100)">{$data.remark}</textarea>
            </td>
        </tr>
        <tr>
            <th class="text-r">用户类型：</th>
            <td>{$data.title}</td>
        </tr>
        <tr>
            <th class="text-r">注册时间：</th>
            <td>{$data.register_time}</td>
        </tr>
        <tr>
            <th class="text-r"></th>
            <td><input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"></td>
        </tr>
        </tbody>
    </table>
    </form>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../../lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../../../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../../../static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
</body>
</html>