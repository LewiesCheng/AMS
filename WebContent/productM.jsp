<%@page import="model.ProductBean"%>
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
<script type="text/javascript">
<!--
	function fun1(){
		return window.confirm("你确定要删除吗？");
	}
-->
</script>
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
		<form action="ProductServlet">
			<h3>查询音像制品:<select name = "need"><option selected="selected">All</option><option>pId</option><option>pType</option><option>pName</option><option>pInventory</option></select>
				<input type="text" name="needValue">
				<input type="submit" value="查询">
				<input type="hidden" name="flag" value="fenye">
				<input type="hidden" name="search" value="notAll">
				<input type="hidden" name="pageNow" value="1">
			</h3>
		</form>
		<h3><a href=addData.jsp?page=productM>添加音像制品</a></h3>
		
		<%
		
		@SuppressWarnings("unchecked")
		ArrayList<ProductBean> arrayList = (ArrayList<ProductBean>)request.getAttribute("pResult");
	
		%>
		<table border = "1" width = "50%">
			<tr align="center"><td>音像制品id</td><td>类型</td><td>名字</td><td>库存</td><td>价格/天</td><td>修改信息</td><td>删除制品</td></tr>
			<%
				for(int i = 0; i < arrayList.size(); i++){
					//从arrayList中取出CustomerBean
					ProductBean productBean = (ProductBean)arrayList.get(i);
					%>
					<tr align="center">
						<td><%=productBean.getpID() %></td>
						<td><%=productBean.getpType() %></td>
						<td><%=productBean.getpName() %></td>
						<td><%=productBean.getpInventory() %></td>
						<td><%=productBean.getpPriceOneDay() %></td>
						<td><a href="updateData.jsp?page=productM&pId=<%=productBean.getpID() %>&pType=<%=productBean.getpType() %>&pName=<%=productBean.getpName() %>&pInventory=<%=productBean.getpInventory() %>&pPriceOneDay=<%=productBean.getpPriceOneDay() %>">修改信息</a></td>
						<td><a onclick="return fun1();" href=ProductServlet?flag=delete&pId=<%=productBean.getpID() %>>删除制品</a></td>
					</tr>
					<%
				}
			%>
		</table><br>
		
		<!--分页-->
		<%
			try{
				
				int pageNow = 0;
				if(arrayList.size() != 0){
					pageNow = Integer.parseInt("" + request.getAttribute("pPageNow"));
					out.println("-" + pageNow + "-<br>");
				} else {
					out.println("<h2>对不起，未查询到符合要求的音像制品。</h2>");
				}
				
				int pageCount = Integer.parseInt("" + request.getAttribute("pPageCount"));
				if(pageCount > 1){
					String search = (String)request.getAttribute("search");
					String need = (String)request.getAttribute("need");
					String value = (String)request.getAttribute("value");
					//上一页
					if(pageNow != 1){
						out.println("<a href=ProductServlet?flag=fenye&pageNow="+(pageNow-1)+"&search="+search+"&need="+need+"&needValue="+value+">上一页</a>" + "  ");
					}
					
					for(int i = 1; i <= pageCount; i++) {
						out.println("<a href=ProductServlet?flag=fenye&pageNow="+i+"&search="+search+"&need="+need+"&needValue="+value+">["+ i +"]</a>"+ "  ");
					}
					
					//下一页
					if(pageNow != pageCount){
						out.println("<a href=ProductServlet?flag=fenye&pageNow="+(pageNow+1)+"&search="+search+"&need="+need+"&needValue="+value+">下一页</a>");
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