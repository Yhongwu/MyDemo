<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shiro整合</title>
</head>
<body>
	<div align="center"><font size="3">用户列表 <a href="<%=request.getContextPath() %>/logout">注销</a></font></div>
	<table align="center" border="2">
		<tr>
			<th>id</th>
			<th>姓名</th>
			<th>是否锁定</th>
			<th>操作</th>
		</tr>
		<c:forEach items='${list}' var='user'>
			<td>${user.id}</td>
			<td><a href="<%=request.getContextPath() %>/members/detail/${user.id}">${user.username}</a></td>
			<td>${user.locked}</td>
			<td>修改 删除</td>
		</c:forEach>
	</table>
</body>
</html>