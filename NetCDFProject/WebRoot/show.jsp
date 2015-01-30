<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>数据展示</title>
	<%@include file="../index.html" %>
	<script>
		$(function(){
		});
		
		function show() {
			window.location.href="show.do";
		}
		
		function showVariable(fileName) {
			var content = $("#varContent");
			$.ajax({
				url: "showVariable.do?fileName="+fileName,
				type: "POST",
				dataType: "text",
				success: function(data) {
					content.text("");
					content.append(fileName+"文件的数据为：<br>"+data);
				}
			}); 
		}
		
		function showAll(fileName) {
			var content = $("#allInfo");
			$.ajax({
				url: "showAll.do?fileName="+fileName,
				type: "POST",
				dataType: "json",
				success: function(data) {
					var objs = eval(data);
					var obj = objs[0];
					alert(obj.dimList[0].shortName);
				}
			}); 
		}
	</script>
</head>
<body>
	<br>
	<div class="container" style="top:10px">
		<ol class="breadcrumb">
		  <li><a href="#">数据处理</a></li>
		  <li class="active">数据显示</li>
		</ol>
		<div class="row">
			<div class="col-sm-3">
				<div class="">
					<div class="">
						<h4>上传文件</h4>
					</div>
					<div class="">
						<div class="">
							<input type="file" id="id-input-file-2" />
						</div>
					</div>
				</div>
				<br><br>
				<div class="">
					<div class="">
						<button onclick="show()">显示文件</button>
					</div>
					<div>
						<c:forEach var="file" begin="0" items="${fileList}">
						     <span class="glyphicon glyphicon-file"></span>
						     <a href="javascript:void(0)" onclick="showAll('${file }')" title="查看数据">${file }</a>
						     <br>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div id="allInfo">
					
				</div>
			</div>
			<div class="col-sm-6">
				<div id="varContent">
				
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-4">
				
			</div>
		</div>
	</div>
	
</body>
</html>


