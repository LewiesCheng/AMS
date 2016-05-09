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
			<h2>需付款信息如下</h2>
			<%
				String hId = (String)request.getAttribute("hId");
				String hReturnDay = (String)request.getAttribute("hReturnDay");
				int hTotalPrice = Integer.parseInt(request.getAttribute("hTotalPrice")+"");
				int hDeposit = Integer.parseInt(request.getAttribute("hDeposit")+"");
			%>
			
			<table border="2" width="30%">
				<tr><td>租赁id:</td><td><%=hId %></td></tr>
				<tr><td>结账日期:</td><td><%=hReturnDay %></td></tr>
				<tr><td>应收金额:</td><td><%=hTotalPrice %></td></tr>
				<tr><td>返回押金:</td><td><%=hDeposit %></td></tr>				
			</table>
			<h2>总计：
				<%
					if(hTotalPrice >= hDeposit){
						out.println("收取  "+(hTotalPrice-hDeposit)+" 元");
					} else {
						out.println("退还  "+(hDeposit-hTotalPrice)+" 元");
					}
				%>
			</h2>
			<form action="HireServlet" method="post">
				<input type="hidden" name="hId" value=<%=hId %>>
				<input type="hidden" name="hReturnDay" value=<%=hReturnDay %>>
				<input type="hidden" name="hTotalPrice" value=<%=hTotalPrice %>>
				<input type="hidden" name="flag" value="hireReturnAfter">
				<input type="submit" value="确认付款">&nbsp;&nbsp;&nbsp;
				<a href="#" onClick="javascript :history.back(-1);"><input type="button" value="取消付款"></a>
			</form>
		</center>
	</div>
	</center>
	<h3 align = right><a href = WelcomeClServlet?requestPage=quit>安全退出</a></h3>
</body>
</html>