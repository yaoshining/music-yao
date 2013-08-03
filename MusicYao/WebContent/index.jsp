<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath()+"/" %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>音乐播放器</title>
<script type="text/javascript" src="resources/scripts/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/scripts/jquery/jplayer/jquery.jplayer.js"></script>
<script type="text/javascript">
<!--
$(function(){
//	var getPercent = setInterval(function(){
//		$.ajax({
//			type: 'get',
//			url: 'music/init/status',
//			success: function(data){
//				$("#status").text(data);
//			}
//		});
//	},1000)
	$("#initBtn").on("click",function(){
		alert($("#directory").val());
		$.ajax({
			type: 'post',
			url: 'music/init',
			data: $("#directory").val(),
			success: function(data){
			}
		});
	});
//	$.ajax({
//		type: 'post',
//		url: 'music/init',
//		success: function(data){
//		}
//	});
//	getPercent();
});
//-->
</script>
</head>
<body>
<input id="directory" type="text" /><button id="initBtn">初始化</button>
<div id="status"></div>
</body>
</html>