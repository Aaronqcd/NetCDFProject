<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>主页</title>
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="dist/js/jquery-1.11.2.js"></script>
    <script src="dist/js/bootstrap.min.js"></script>
    <script src="dist/js/bootstrap-contextmenu.js"></script><%--
    oncontextmenu='return false'--%><script src="dist/js/holder.js"></script>
    <script src="dist/layer/layer.min.js"></script>
    <script src="js/main.js"></script>
</head>
<body >
	<div>
		<div class="row">
			<div class="">
				<div class="">
					<ol class="breadcrumb">
					  <li><a href="#">数据处理</a></li>
					  <li class="active">数据显示</li>
					</ol>
					<button type="button" class="btn btn-primary" onclick="show()">显示文件</button>
				</div>
			</div>
		</div>
		
		<div class="row">
			<!-- 显示所有的文件 -->
			<div id="allFile" class="col-md-2">
			</div>
			<div class="col-md-2" >
				<!-- 显示维度和变量 -->
				<div id="allInfo" data-toggle="context"> <!-- oncontextmenu="display()" data-toggle="context" data-target="#context-menu"用于显示右键菜单 -->
					
				</div>
			</div>
			<div class="col-md-8">
				<div id="varContent" >
					<!-- 显示变量的详细信息 -->
					<table id="table1" class="table table-bordered" style="display:none;width:500px;">
					</table>
				</div>
			</div>
		</div>
		<!-- 右键菜单显示的内容 -->
		<div id="context-menu">
	      	<ul class="dropdown-menu" role="menu">
	      		<input type="hidden" id="vari1" value=""/>
                <li><a id="state" herf="javascript:void(0)" onclick="display()">声明展示</a></li>
	            <li><a>NcML展示</a></li>
	      	</ul>
	    </div>
	</div>
</body>
</html>


