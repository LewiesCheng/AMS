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
		
		<form action="HireServlet" method=get>
			<h3>
				查询用户:<select name="need"><option selected="selected">All</option><option>hId</option><option>hCustomerId</option>
				<option>hProductId</option><option>hBeginDay</option><option>hReturnDay</option><option>hWhetherReturn</option></select>
				<input type="text" name="needValue">
				<input type="submit" value="查询">
				<input type="hidden" name="flag" value="fenye">
				<input type="hidden" name="search" value="notAll">
				<input type="hidden" name="pageNow" value="1">
			</h3>
		</form>
		<%
			@SuppressWarnings("unchecked")
			ArrayList<HireBean> arrayList = (ArrayList<HireBean>)request.getAttribute("hResult");
		%>
		
		<table border="1" width="60%">
			<tr align="center">
				<td>租赁id</td><td>客户id</td><td>制品id</td><td>制品数量</td><td>出租日期</td><td>归还日期</td><td>押金/元</td><td>已付款/元</td><td>是否归还</td>
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
				<td><%=hireBean.gethReturnDay() %></td>
				<td><%=hireBean.gethDeposit() %></td>
				<td><%=hireBean.gethTotalPrice() %></td>
				<%
					if(hireBean.gethWhetherReturn().equals("yes")){
						%>
						<td>已归还</td>
						<%
					}else{
						%>
						<td bgcolor="red">未归还</td>
						<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
		<br>
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
					String search = (String)request.getAttribute("search");
					String need = (String)request.getAttribute("need");
					String value = (String)request.getAttribute("value");
					//上一页
					if(pageNow != 1){
						out.println("<a href=HireServlet?flag=fenye&pageNow="+(pageNow-1)+"&search="+search+"&need="+need+"&needValue="+value+">上一页</a>" + "  ");
					}
					
					for(int i = 1; i <= pageCount; i++) {
						out.println("<a href=HireServlet?flag=fenye&pageNow="+i+"&search="+search+"&need="+need+"&needValue="+value+">["+ i +"]</a>"+ "  ");
					}
					
					//下一页
					if(pageNow != pageCount){
						out.println("<a href=HireServlet?flag=fenye&pageNow="+(pageNow+1)+"&search="+search+"&need="+need+"&needValue="+value+">下一页</a>");
					}
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		%>	
	</div>
	</center>
	<h3 align = right><a href = WelcomeClServlet?requestPage=quit>安全退出</a></h3>
</body>
</html>