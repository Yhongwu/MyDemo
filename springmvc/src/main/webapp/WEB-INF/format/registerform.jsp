<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>converter转换器测试</title>
</head>
<body>
	<h3>注册页面</h3>
	<form action="format/register" method="post">
		<table>
			<tr>
				<td><label>登录名：</label></td>
				<td><input type="text" id="loginName" name="loginName"></td>
			</tr>
			<tr>
				<td><label>生日：</label></td>
				<td><input type="text" id="birthday" name="birthday"/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" id="submit" value="登录">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>