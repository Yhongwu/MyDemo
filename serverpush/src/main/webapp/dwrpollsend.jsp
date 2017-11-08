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
	<input type="text" id="msg"  />  
    <input type="button" value="发送" id="btn" />  
</body>
<script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>    
<script type="text/javascript" src="dwr/engine.js"></script>   
<script type="text/javascript" src="dwr/interface/sendMsg.js"></script>  
<script type="text/javascript">
	$(function(){  
	    dwr.engine.setActiveReverseAjax(true);  
	    alert('asd');
	    $("#btn").click(function(){  
	        sendMsg.sendMsg($("#msg").val());  
	    });   
	});  
</script>
</html>