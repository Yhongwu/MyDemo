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
		未读消息<span id="num">5</span>条...
	</div>
</body>
<script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		getNum();
		//alert("asd");
		
	});
	function getNum(){
		$.ajax({
	        url:'ajaxpull',
	        type:'post',
	        dataType:'json',
	        timeout:50000,
	        success:function(data, textStatus){
	            if(data && data.num){ //请求成功，刷新数据
	                $("#num").html(data.num);//这个是用来和后台数据作对比判断是否发生了改变
	            } 
	            if(textStatus == "success"){
	            	getNum();//成功之后，再发送请求，递归调用
	            }
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	            if(textStatus == "timeout"){
	            	alert("asd");
	            	getNum();//有效时间内没有响应，请求超时，重新发请求
	            }else{
	                getNum(); // 其他的错误，如网络错误等
	            }
	        }
	    });
	}
</script>
</html>