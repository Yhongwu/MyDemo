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
	<form action="format/formatter" method="post">
		<table>
			<tr>
				<td><label>日期：</label></td>
				<td><input type="text" id="birthday" name="birthday"></td>
			</tr>
			<tr>
				<td><label>整数：</label></td>
				<td><input type="text" id="total" name="total"/>
				</td>
			</tr>
			<tr>
				<td><label>百分数：</label></td>
				<td><input type="text" id="discount" name="discount"></td>
			</tr>
			<tr>
				<td><label>货币：</label></td>
				<td><input type="text" id="money" name="money"/>
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