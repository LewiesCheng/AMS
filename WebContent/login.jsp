<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>音像租赁管理系统</title>
</head>
<body>
	<center>
		<br><h1><font face = "楷体" size = 6>欢迎使用音像租赁管理系统</font></h1><br>
		<hr width = "60%" ><br>
		<form action="LoginClServlet" method = "post">
			用户名：<input type = "text" name = "username"><br><br>
			密&nbsp;码：<input type = "password" name = "passwd"><br><br>
			<input type = "submit" value = "登录">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type = "reset" value = "重置">
		</form>
	</center>
</body>
</html>