<%@page import="model.CustomerBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>音像租赁管理系统</title>

<style type="text/css">
h1 {font-family: "华文行楷";font-size: 300%;color: green}
h2 {font-family: "楷体";font-size: 25px;color: blue}
</style>

<script type="text/javascript">
<!--
	function fun1() {
		return window.confirm("你确定要删除吗？");
	}
	-->
</script>
</head>

<body>
	<%
		//防止用户非法登录
		if ((String) session.getAttribute("adminName") == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<center>
		<h1>音像租赁管理系统</h1>
		 用户名：<%=(String) session.getAttribute("adminName")%>
		<hr style="border: 3 double #987cb9" width="80%" color=#987cb9 SIZE=3>
		<table border="0" width="75%">
			<tr height="50">
				<td align="center"><h2><a href=WelcomeClServlet?requestPage=customerM>客户管理</a></h2></td>
				<td align="center"><h2><a href=WelcomeClServlet?requestPage=productM>音像资料管理</a></h2></td>
				<td align="center"><h2><a href=WelcomeClServlet?requestPage=hireM>租赁管理</a></h2></td>
				<td align="center"><h2><a href=StatisticsServlet>业务统计</a></h2></td>
			</tr>
		</table>

		<div>
			<!--编辑各功能页面的详细-->

			<form action="CustomerServlet" method=get>
				<h3>
					查询用户:<select name="need"><option selected="selected">All</option><option>cId</option><option>cName</option></select>
					<input type="text" name="needValue">
					<input type="submit" value="查询">
					<input type="hidden" name="flag" value="fenye">
					<input type="hidden" name="search" value="notAll">
					<input type="hidden" name="pageNow" value="1">
				</h3>
			</form>
			<h3>
				<a href=addData.jsp?page=customerM>添加客户</a>
			</h3>
			<%
				@SuppressWarnings("unchecked")
				ArrayList<CustomerBean> arrayList = (ArrayList<CustomerBean>) request.getAttribute("cResult");
			%>

			<table border="1" width="50%">
				<tr align="center">
					<td>客户id</td><td>客户名</td><td>密码</td><td>联系方式</td><td>修改用户</td><td>删除用户</td>
				</tr>
				<%
					for (int i = 0; i < arrayList.size(); i++) {
						//从arrayList中取出CustomerBean
						CustomerBean customerBean = (CustomerBean) arrayList.get(i);
				%>
				<tr align="center">
					<td><%=customerBean.getcId()%></td>
					<td><%=customerBean.getcName()%></td>
					<td><%=customerBean.getcPassword()%></td>
					<td><%=customerBean.getcContactWay()%></td>
					<td><a
						href="updateData.jsp?page=customerM&cId=<%=customerBean.getcId()%>&cName=<%=customerBean.getcName()%>&cPassword=<%=customerBean.getcPassword()%>&cContactWay=<%=customerBean.getcContactWay()%>">修改用户</a></td>
					<td><a onclick="return fun1();"
						href=CustomerServlet?flag=delete&cId=<%=customerBean.getcId()%>>删除用户</a></td>
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
						pageNow = Integer.parseInt("" + request.getAttribute("cPageNow"));
						out.println("-" + pageNow + "-<br>");
					} else {
						out.println("<h2>对不起，未查询该用户。</h2>");
					}
					
					if(request.getAttribute("search").equals("all")){
						//上一页
						if (pageNow != 1) {
							out.println("<a href=CustomerServlet?flag=fenye&pageNow=" + (pageNow - 1) + "&search=all>上一页</a>" + "  ");
						}

						int pageCount = Integer.parseInt("" + request.getAttribute("cPageCount"));

						for (int i = 1; i <= pageCount; i++) {
							out.println("<a href=CustomerServlet?flag=fenye&pageNow=" + i + "&search=all>[" + i + "]</a>" + "  ");
						}

						//下一页
						if (pageNow != pageCount) {
							out.println("<a href=CustomerServlet?flag=fenye&pageNow=" + (pageNow + 1) + "&search=all>下一页</a>");
						}
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
			%>

		</div>
	</center>
	<h3 align=right>
		<a href=WelcomeClServlet?requestPage=quit>安全退出</a>
	</h3>
</body>
</html>