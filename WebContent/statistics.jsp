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
	h3{font-family:"宋体";font-size:20px;color:blue}
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
	<h1>欢迎使用音像租赁管理系统</h1>
	用户名：<%=(String)session.getAttribute("adminName") %>
	<hr style="border:3 double #987cb9" width="80%" color=#987cb9 SIZE=3>
	<table border = "0" width = "75%">
		<tr height="50">
			<td align="center"><h2><a href=WelcomeClServlet?requestPage=customerM>客户管理</a></h2></td>
			<td align="center"><h2><a href=WelcomeClServlet?requestPage=productM>音像资料管理</a></h2></td>
			<td align="center"><h2><a href=WelcomeClServlet?requestPage=hireM>租赁管理</a></h2></td>
			<td align="center"><h2><a href=StatisticsServlet>业务统计</a></h2></td>
		</tr>
	</table>
	
	<div>
		<table width="30%" border="2">
		    <tr align="center">
		      <td rowspan="4">总计：</td>
		      <td>拥有客户/名</td><td><%=request.getAttribute("customerNumber") %></td>
		    </tr>
		    <tr align="center">
		      <td>租出音频/个</td><td><%=request.getAttribute("audioNumber") %></td>
		    </tr>
		    <tr align="center">
		      <td>收&nbsp;益/元</td><td><%=request.getAttribute("totalProfits") %></td>
		    </tr>
		    <tr align="center">
		      <td>未退押金/元</td><td><%=request.getAttribute("remainDeposit") %></td>
		    </tr>
		    <tr align="center">
		      <td rowspan="4">今天：</td>
		      <td>租出音频/个</td><td><%=request.getAttribute("todayHire") %></td>
		    </tr>
		    <tr align="center">
		      <td>收回音频/个</td><td><%=request.getAttribute("todayReturn") %></td>
		    </tr>
		    <tr align="center">
		      <td>返回押金/元</td><td><%=request.getAttribute("todayReturnDeposit") %></td>
		    </tr>
		    <tr align="center">
		      <td>收&nbsp;益/元</td><td><%=request.getAttribute("todayProfits") %></td>
		    </tr>
	  	</table>
	
	</div>
	</center>
	<h3 align = right><a href = WelcomeClServlet?requestPage=quit>安全退出</a></h3>
</body>
</html>