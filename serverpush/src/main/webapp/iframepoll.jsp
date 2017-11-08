<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.tip{
		padding: 50px 100px 100px 100px;
		margin: 30px 100px 100px 0;
		text-align: center;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="tip">
		未读消息<span id="num">5</span>条...
	</div>
<iframe id="polling" name="polling" src='iframepoll' style="display:none;"></iframe>
</body>
<script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	function getNum(num){
	    $('#num').html(num);
	    //获取到数据后继续开始新的连接
	    $("#polling").attr("src","iframepoll");
	}
</script>
</html>