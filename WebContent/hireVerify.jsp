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
	<table border = "0" width = "75%">
		<tr height="50">
			<td align="center"><h2><a href=WelcomeClServlet?requestPage=customerM>客户管理</a></h2></td>
			<td align="center"><h2><a href=WelcomeClServlet?requestPage=productM>音像资料管理</a></h2></td>
			<td align="center"><h2><a href=WelcomeClServlet?requestPage=hireM>租赁管理</a></h2></td>
			<td align="center"><h2><a href=StatisticsServlet>业务统计</a></h2></td>
		</tr>
	</table>

	<div>
		<!--编辑各功能页面的详细-->
		<center>
			<h2>验证客户</h2>
			<form action="HireServlet" method="post">
			客户名：<input type="text" name="cName"><br><br>
			密&nbsp;码：<input type="password" name="cPassword"><br><br>
			<input type="hidden" name="flag" value="verify">
			<input type="hidden" name="begAndRet" value=<%=request.getParameter("begAndRet") %>>
			<input type="submit" value="验证客户">&nbsp;&nbsp;
			<input type = "reset" value = "重置信息">
			</form>
			<br><br>
			<%
				if(request.getParameter("begAndRet").equals("begin")){
					%>
					<h3>如果还不是客户，可点下面直接进行注册</h3>
					<a href="addData.jsp?page=customerM">注册新客户</a>
					<%
				}
			%>
		</center>
	</div>
	</center>
	<h3 align = right><a href = WelcomeClServlet?requestPage=quit>安全退出</a></h3>
</body>
</html>