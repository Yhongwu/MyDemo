<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.tip{
		padding: 50px 100px 100px 100px;
		margin: 30px 100px 100px 0;
		text-align: center;
	}
</style>
</head>
<body>
	<div class="tip">
		有新消息：<span id="msg">5</span>...
	</div>
</body>
<script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>    
<script type="text/javascript" src="dwr/engine.js"></script>   
<script type="text/javascript">
	$(function(){  
	    //这句话千万不能少 ，表示允许使用推送技术  
	    dwr.engine.setActiveReverseAjax(true);  
	});  
	function show(msg){  
        $("#msg").html(msg);  
    }  
</script>
</html>