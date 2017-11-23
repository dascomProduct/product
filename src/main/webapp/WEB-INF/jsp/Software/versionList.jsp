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
    <!--[if lt IE 9]>
    <script type="text/javascript" src="__H-UI__/lib/html5shiv.js"></script>
    <script type="text/javascript" src="__H-UI__/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/page.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/js/asset/bootstrap/dist/css/bootstrap.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="__H-UI__/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>权限管理</title>
</head>
<body>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
            <a href="javascript:;" onclick="classify_add('添加版本','addVersion?id=${rid }','','520')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加版本</a>
        </span>
        <span class="r">共有数据：<strong>${sList.total }</strong> 条</span>
    </div>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="8">版本列表</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" name="" value=""></th>
            <th width="40">序列号</th>
            <th width="160">版本名称</th>
            <th width="50">版本号</th>
            <th width="70">系统</th>
            <th width="50">后缀名</th>
            <th>描述</th>
            <th width="50">是否新版</th>
            <th width="70">操作</th>
        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${sList.dataList}" varStatus="status" var="vo">
	        	<tr class="text-c">
	                <td><input type="checkbox" value="${vo.id}" name="batch"></td>
	                <td>${status.index+1}</td>
	                <td>${vo.version}</td>
	                <td>${vo.versionNum}</td>
	                <td>${vo.system}</td>
	                <td>${vo.suffix}</td>
	                <td>${vo.describe}</td>
	                <td>
	                	<c:if test="${vo.isNew==1 }">
	                		是
	                	</c:if>
	                	<c:if test="${vo.isNew!=1 }">
	                		否
	                	</c:if>
	                </td>
	                <td>
	                    <a title="编辑" href="javascript:;" onclick="classify_edit('版本编辑','editVersion','${vo.id}','','520')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
	                    <a title="删除" href="javascript:;" onclick="classify_del(this,'${vo.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
	                </td>
	            </tr>
	        </c:forEach>
        </tbody>
    </table>
</div>
<div id="paging"></div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath %>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath %>/js/asset/bootstrap/dist/js/bootstrap.min.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
    /*版本-添加*/
    function classify_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*版本-编辑*/
    function classify_edit(title,url,id,w,h){
        var url=url+'?id='+id;
        layer_show(title,url,w,h);
    }

    /*版本-删除*/
    function classify_del(obj,id){
        $count=$('.r strong').html();
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: 'delVersion',
                data:{id:id},
                dataType: 'json',
                success: function(data){
                    if (data.code=='1000') {
                        if ($.isArray(obj)) {
                            for (var i=0;i<obj.length;i++) {
                                $(obj[i]).parents("tr").remove();
                            }
                            $('.r strong').html($count-obj.length);
                        }else{
                            $(obj).parents("tr").remove();
                            $('.r strong').html($count-1)
                        }
                        layer.msg(data.msg,{icon:1,time:1500});
                    }else{
                        layer.msg(data.msg,{icon:2,time:1500});
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }
    /*版本-批量删除*/
    function datadel() {
        var id='';
        var arr=[];
        $("input[name='batch']").each(function () {
            var obj=$(this);
            if (obj.get(0).checked) {
                id+=','+obj.val();
                arr.push(obj);
            }
        });
        if (id=='') {
            layer.msg('请选择删除项');
            return false;
        }
        id=id.substring(1);
        console.log(id);
        classify_del(arr,id)
    }
    
    
    $(function() {
		/* 加载分页  */
		//当前页
		var pageNumber = parseInt('${sList.pageNo}');
		//页面大小
		var pageSize = parseInt('${sList.pageSize}');
		//总页数
		var pages = parseInt('${sList.pages}');
		//总条数
		var pageTotal = parseInt('${sList.total}');
		//页面地址
		var pageStart = "href='versionList?id="+'${rid}'+"&pageNumber=";
		var pageEnd = "'";

		if(pages == 0) pages = 1;

		var paging = "" +
			"<div class='pull-right'  style='padding: 0px 10%  0px 10%'' >" +
			"<form class='form-inline' role='form'>" +
			"<div class='form-group'>";
		if(pages == pageNumber) {
			paging = paging + "当前页" + (((pageNumber - 1) * pageSize) + 1) + "~" + pageTotal + "条 ,共 " + pageTotal + " 条";
		} else {
			paging = paging + "当前页" + (((pageNumber - 1) * pageSize) + 1) + "~" + (pageNumber * pageSize) + "条 ,共 " + pageTotal + " 条";
		}
		paging = paging +
			"</div>" +
			"<div class='form-group'>" +
			"<ul class='pagination'>";
		if(pageNumber <= 1) {
			paging = paging + "<li class='disabled'><a >上一页</a></li>";
		} else {
			paging = paging + "<li ><a " + pageStart + (pageNumber - 1) + pageEnd + " >上一页</a></li>";
		}
		if(pageNumber <= 3) {
			for(var i = 1; i <= pages && i <= 5; i++) {
				if(i == pageNumber) {
					paging = paging + "<li class='active' ><a " + pageStart + i + pageEnd + ">" + i + "</a></li>";
				} else {
					paging = paging + "<li><a " + pageStart + i + pageEnd + ">" + i + "</a></li>";
				}

			}
		} else if(pageNumber >= 3 && pageNumber <= (pages - 2)) {
			for(var i = (pageNumber - 2); i <= (pageNumber + 2); i++) {
				if(i == pageNumber) {
					paging = paging + "<li class='active' ><a " + pageStart + i + pageEnd + ">" + i + "</a></li>";
				} else {
					paging = paging + "<li><a " + pageStart + i + pageEnd + ">" + i + "</a></li>";
				}
			}
		} else if(pageNumber >= (pages - 2)) {
			for(var i = 4; i >= 0; i--) {
				if((pages - i) == pageNumber) {

					paging = paging + "<li class='active' ><a " + pageStart + (pages - i) + pageEnd + " >" + (pages - i) + "</a></li>";

				} else {
					if((pages - i) > 0) {
						paging = paging + "<li><a " + pageStart + (pages - i) + pageEnd + " >" + (pages - i) + "</a></li>";
					}
				}
			}
		}

		if(pageNumber >= (pages)) {
			paging = paging + "<li class='disabled'><a>下一页</a></li>";
		} else {
			paging = paging + "<li ><a " + pageStart + (pageNumber + 1) + pageEnd + " >下一页</a></li>";
		}
		paging = paging +
			"</ul>" +
			"</div>" +
			"</form>" +
			"</div>";
		$("#paging").html(paging);
		/*alert (pageNumber+""+pageSize+""+pages+""+pageTotal);*/
	});
    
</script>
</body>
</html>