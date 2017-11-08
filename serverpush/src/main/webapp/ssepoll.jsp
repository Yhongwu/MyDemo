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
	/* $(function(){
		alert('asd');
	}); */
    var requestPath = "ssepoll" ;
    if(typeof (EventSource) !== "undefined" ) {
         var source = new EventSource(requestPath);
         source.onmessage = function(event) {
              var num = event.data;
              $("#num").html(num);
              //source.close(); //关闭连接
         };
         source.onerror = function(event) {
        	  // handle error event
         };
         //处理message或error的另一种方式
         /* source.addEventListener("message/error", function(event) {
        	  // handle error event
         }, false); */
    } else{
          tool.msg( "当前浏览器不兼容，请换用chrome" );
  }
  </script >
</html>