<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>WebSocket示例wadawd</title>
</head>
<body>
	<form action="api/login" method="post">
		用户名:
		<select name="id">
			<option value="1">男神</option>
			<option value="2">女神</option>
		</select><br>
		密码:
		<input name="password" type="text" value="123456">
		<input type="submit" value="登录">
	</form>
	
	<br>
	手机端登录
	<form action="api/mobileLogin" method="post">
		用户名:
		<select name="id">
			<option value="1">男神</option>
			<option value="2">女神</option>
		</select><br>
		密码:
		<input name="password" type="text" value="123456">
		<input type="submit" value="登录">
	</form>
</body>

</html>
