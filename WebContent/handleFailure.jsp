<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

	<h3>很遗憾，操作失败。请重新操作！</h3>
	<%
		if(request.getAttribute("page").equals("customerM")) {
			out.println("<a href = WelcomeClServlet?requestPage=customerM>返回客户管理页面</a>");
		} else if (request.getAttribute("page").equals("productM")){
			out.println("<a href = WelcomeClServlet?requestPage=productM>返回音像资料管理页面</a>");
		} else if (request.getAttribute("page").equals("hireM")){
			if(request.getAttribute("flag").equals("begin")){
				%>
				<a href="#" onClick="javascript :history.back(-1);">继续添加出租信息</a><br><br>
				<%
			}else if(request.getAttribute("flag").equals("return")){
				%>
				<a href="#" onClick="javascript :history.go(-2);">继续添加归还信息</a><br><br>
				<%
			}
			out.println("<a href = WelcomeClServlet?requestPage=hireM>返回租赁管理页面</a>");
		}
	%>
	</center>
	<h3 align = right><a href = WelcomeClServlet?requestPage=quit>安全退出</a></h3>
</body>
</html>