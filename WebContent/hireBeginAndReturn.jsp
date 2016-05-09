<%@page import="model.HireBean"%>
<%@page import="java.util.ArrayList"%>
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
		<%
			if(request.getAttribute("flag").equals("begin")){
				%>
				<center>
					<h2>验证成功，添加出租信息</h2>
					<form action="HireServlet" method="post">
					<table border="1">
						<tr align="center"><td>客户id:</td><td><input type="text"  readonly="readonly" name="hCustomerId" value=<%=request.getAttribute("cId") %>></td></tr>
						<tr align="center"><td>制品id:</td><td><input type="text" name="hProductId"></td></tr>
						<tr align="center"><td>数&nbsp;量:</td><td><input type="text" name="hNumber"></td></tr>
						<tr align="center"><td>出租日期:</td><td><input type="text" readonly="readonly" name="hBeginDay" value=<%=request.getAttribute("todayDate") %>></td></tr>
						<tr align="center"><td>压&nbsp;金:</td><td><input type="text" name="hDeposit"></td><td background="white">*一本/50元</td></tr>
					</table><br>
					<input type="hidden" name="flag" value="hireBegin">
					<input type = "submit" value = "确认添加">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "reset" value = "重置信息">
				</form>
				</center>
				<%
			}else if(request.getAttribute("flag").equals("return")){
				%>
				<center>
				<h2>验证成功，查询到<%=request.getAttribute("cName") %>的租用记录</h2>
				<%
				@SuppressWarnings("unchecked")
				ArrayList<HireBean> arrayList = (ArrayList<HireBean>)request.getAttribute("hResult");
				%>
		
				<table border="1" width="60%">
				<tr align="center">
					<td>租赁id</td><td>客户id</td><td>制品id</td><td>出租数量</td><td>出租日期</td><td>押金/元</td><td>是否归还</td>
				</tr>
				<%
				for (int i = 0; i < arrayList.size(); i++) {
					//从arrayList中取出HireBean
					HireBean hireBean = (HireBean)arrayList.get(i);
				%>
				<tr align="center">
					<td><%=hireBean.gethId() %></td>
					<td><%=hireBean.gethCustomerId() %></td>
					<td><%=hireBean.gethProductId() %></td>
					<td><%=hireBean.gethNumber() %></td>
					<td><%=hireBean.gethBeginDay() %></td>
					<td><%=hireBean.gethDeposit() %></td>
					<td bgcolor="red">未归还</td>
				</tr>
				<%
				}
				%>
				</table>
				<%
					try {
						
						int pageNow = 0;
						if(arrayList.size() != 0){
							pageNow = Integer.parseInt("" + request.getAttribute("hPageNow"));
							out.println("-" + pageNow + "-<br>");
						} else {
							out.println("<h2>对不起，未查询到符合要求的租赁信息。</h2>");
						}
						
						int pageCount = Integer.parseInt("" + request.getAttribute("hPageCount"));
						if(pageCount > 1){
							String cId = (String)request.getAttribute("cId");
							String cName = (String)request.getAttribute("cName");
							//上一页
							if(pageNow != 1){
								out.println("<a href=HireServlet?flag=hireReturn&pageNow="+(pageNow-1)+"&cId="+cId+"&cName="+cName+">上一页</a>" + "  ");
							}
							
							for(int i = 1; i <= pageCount; i++) {
								out.println("<a href=HireServlet?flag=hireReturn&pageNow="+i+"&cId="+cId+"&cName="+cName+">["+ i +"]</a>"+ "  ");
							}
							
							//下一页
							if(pageNow != pageCount){
								out.println("<a href=HireServlet?flag=hireReturn&pageNow="+(pageNow+1)+"&cId="+cId+"&cName="+cName+">下一页</a>");
							}
						}
					} catch (Exception e) {
		
						e.printStackTrace();
					}
				%>
				<br>
				<h2>填写归还信息</h2>
				<form action="HireServlet" method="post">
					<table border="1">
						<tr align="center"><td>租赁id</td><td>客户id</td><td>归还日期</td></tr>
						<tr align="center">
							<td><input type="text" name="hId"></td>
							<td><input type="text"  readonly="readonly" name="hCustomerId" value=<%=request.getAttribute("cId") %>></td>
							<td><input type="text" name="hReturnDay" readonly="readonly" value=<%=request.getAttribute("todayDate") %>></td>
						</tr>
					</table><br>
					<input type="hidden" name="flag" value="hireReturnMoney">
					
					<input type = "submit" value = "下一步">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "reset" value = "重置信息">
				</form>
				</center>
				<%
			}
		%>	
	</div>
	</center>
	<h3 align = right><a href = WelcomeClServlet?requestPage=quit>安全退出</a></h3>
</body>
</html>