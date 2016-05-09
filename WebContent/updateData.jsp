<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>音像租赁管理系统</title>
<style type="text/css">
	h1{font-family:"华文行楷";font-size:300%;color:green}
	h2{font-family:"楷体";font-size:25px;color:blue}
</style>
</head>
<body>
	<%
		//防止用户非法登录
		if((String)session.getAttribute("adminName") == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<center>
	<h1 >音像租赁管理系统</h1>
	用户名：<%=(String)session.getAttribute("adminName") %>
	<hr style="border:3 double #987cb9" width="80%" color=#987cb9 SIZE=3>
	<div>
		<!--修改信息-->
		<%
			if(request.getParameter("page").equals("customerM")){
				%>
				<!-- 修改客户信息 -->
				<center>
				<h2>请修改客户信息</h2>
				<form action="CustomerServlet?flag=update" method="post">
					<table border="1">
						<tr align="center"><td>用户Id:</td><td><input type="text" readonly="readonly" name="cId" value=<%=request.getParameter("cId") %>></td></tr>
						<tr align="center"><td>用户名:</td><td><input type="text" name="cName" value=<%=request.getParameter("cName") %>></td></tr>
						<tr align="center"><td>密&nbsp;&nbsp;码:</td><td><input type="password" name="cPassword" value=<%=request.getParameter("cPassword") %>></td></tr>
						<tr align="center"><td>联系方式:</td><td><input type="text" name="cContactWay" value=<%=request.getParameter("cContactWay") %>></td></tr>
					</table><br>
					<input type = "submit" value = "确认修改">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "reset" value = "重置信息">
				</form>
				</center>
				<%
			} else if(request.getParameter("page").equals("productM")){
				%>
				<!-- 修改音像制品信息 -->
				<center>
				<h2>请修改音像制品信息</h2>
				<form action="ProductServlet?flag=update" method="post">
					<table border="1">
						<tr align="center"><td>I&nbsp;d:</td><td><input type="text" readonly="readonly" name="pId" value=<%=request.getParameter("pId") %>></td></tr>
						<tr align="center"><td>类&nbsp;型:</td><td><input type="text" name="pType" value=<%=request.getParameter("pType") %>></td></tr>
						<tr align="center"><td>名&nbsp;字:</td><td><input type="text" name="pName" value=<%=request.getParameter("pName") %>></td></tr>
						<tr align="center"><td>库&nbsp;存:</td><td><input type="text" name="pInventory" value=<%=request.getParameter("pInventory") %>></td></tr>
						<tr align="center"><td>价格/天:</td><td><input type="text" name="pPriceOneDay" value=<%=request.getParameter("pPriceOneDay") %>></td></tr>
					</table><br>
					<input type = "submit" value = "确认修改">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "reset" value = "重置信息">
				</form>
				</center>
				
				<%
			} else if(request.getAttribute("page").equals("hireM")){
				%>
				
				
				<%
			}
		%>
	</div>
	</center>
	<h3 align = right><a href = WelcomeClServlet?requestPage=quit>安全退出</a></h3>
</body>
</html>