$(function(){
	$('#allInfo').contextmenu({
        target: '#context-menu',
        before: function(e,element,context) {
        	return true;
		},
        onItem: function (context, e) {
        }
      });
});

/*$('#allInfo').contextmenu({
		
        target: '#context-menu',
        onItem: function (context, e) {
        	alert(1);
          //alert($(e.target).text());
        }
      });*/
      
function display() {
}

function over(vari){
	//alert(1);
	$("#vari1").val(vari);
}
//显示目录下面的所有文件
function show() {
	var content = $("#allFile");
	$.ajax({
		url: "show.do",
		type: "POST",
		dataType: "json",
		success: function(data) {
			var objs = eval(data);
			var obj = objs[0];
			$("#allFile").css("border", "solid 1px #e5e3da");
			content.text("");
			for(var i=0; i<obj.fileList.length; i++) {
				var fileList = "'"+obj.fileList[i]+"'";
				content.append("<span class='glyphicon glyphicon-file'></span>");
				//\""转义字符
			    content.append("<a href='javascript:void(0)' onclick='showAll(\""+obj.fileList[i]+"\")' title='查看维度和变量'>"+obj.fileList[i]+"</a>");
			    content.append("<br>");
			}
		}
	}); 
}
//显示文件中的变量
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
//显示文件中的所有信息
function showAll(fileName) {
	var content = $("#allInfo");
	var con = $("#varContent");
	$.ajax({
		url: "showAll.do?fileName="+fileName,
		type: "POST",
		dataType: "json",
		success: function(data) {
			var objs = eval(data);
			var obj = objs[0];
			$("#allInfo").css("border", "solid 1px #e5e3da");
			content.text("");
			//显示维度
			content.append("<input type='hidden' id='path' value="+fileName+"></input>");
			for(var i=0; i<obj.dimList.length; i++) {
				content.append("<img src='images/icons/dimension.ico' height='20' width='20' />");
				content.append("<a href='javascript:void(0)' onclick='showAll1(\""+obj.dimList[i].shortName+"\")' title='查看详细数据'>&nbsp;"+obj.dimList[i].shortName+"</a>");
				content.append("<br>");
			}
			//显示变量
			for(var i=0; i<obj.varList.length; i++) {
				content.append("<img src='images/icons/variable.ico' height='20' width='20' />");
				content.append("<a href='javascript:void(0)' onmouseover='over(\""+obj.varList[i].shortName+"\")' onclick='showAll1(\""+obj.varList[i].shortName+"\")' title='查看详细数据'>&nbsp;"+obj.varList[i].shortName+"</a>");
				content.append("<br>");
			}
			//显示变量的属性
			$("tr").remove();
			$("#table1").append("<tr id='ftr'><th>variable</th><th>dataType</th><th>description</th><th>dimensions</th><th>group</th><th>name</th><th>shape</th><th>units</th>");
			$("#ftr").after("<tr id='oldTr'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
			var oldTr = $("#oldTr");
			$.each(obj.attList, function(index, item){
				var newTr = oldTr.clone();
				newTr.children("td").each(function(inner_index){
					switch(inner_index){
						case(0):
							$(this).html(item.variable);
							break;
						case(1):
							$(this).html(item.dataType);
							break;
						case(2):
							$(this).html(item.description);
							break;
						case(3):
							$(this).html(item.dimensions);
							break;
						case(4):
							$(this).html(item.group);
							break;
						case(5):
							$(this).html(item.name);
							break;
						case(6):
							$(this).html(item.shape);
							break;
						case(7):
							$(this).html(item.units);
							break;
							
					}
				});
				newTr.insertBefore(oldTr);
			});
			oldTr.hide();
			$("#table1").show();
		}
	}); 
}
//维度和变量的点击事件
function showAll1(name){
	
}