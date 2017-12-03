<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>converter转换器测试</title>
</head>
<body>
	<h3>注册页面</h3>
	<form:form modelAttribute="user" method="post" action="">
		<table>
			<tr>
				<td><label>日期：</label></td>
				<td><form:input path="birthday"/></td>
			</tr>
			<tr>
				<td><label>整数：</label></td>
				<td><form:input path="total"/>
				</td>
			</tr>
			<tr>
				<td><label>百分数：</label></td>
				<td><form:input path="discount"/></td>
			</tr>
			<tr>
				<td><label>货币：</label></td>
				<td><form:input path="money"/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>