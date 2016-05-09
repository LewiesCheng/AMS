package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerBean;
import model.CustomerBeanCl;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("flag").equals("fenye")) {
			
			//分页显示客户的资料信息
			try {
				
				//得到用户希望显示的pageNow
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));
				
				//调用CustomerBeanCl
				CustomerBeanCl customerBeanCl = new CustomerBeanCl();
				//页数
				int pageCount = 1;
				//查询需要显示的客户（默认显示所有客户）
				ArrayList<CustomerBean> arrayList = new ArrayList<>();
				if (request.getParameter("search").equals("all")) {
					
					//显示所有客户
					arrayList= customerBeanCl.getCustomerByPage(pageNow, "all", "null");
					//页数
					pageCount = customerBeanCl.getPageCount();
					request.setAttribute("search", "all");
					
				} else if (request.getParameter("search").equals("notAll")) {
					if(request.getParameter("need").equals("All")){
						//显示所有客户
						arrayList= customerBeanCl.getCustomerByPage(pageNow, "all", "null");
						//页数
						pageCount = customerBeanCl.getPageCount();
						request.setAttribute("search", "all");
						
					} else {
						//按要求查询客户
						arrayList= customerBeanCl.getCustomerByPage(pageNow, request.getParameter("need"), request.getParameter("needValue"));
					}
				} 
				
				
				
				request.setAttribute("cResult", arrayList);
				request.setAttribute("cPageCount", pageCount+"");
				request.setAttribute("cPageNow", pageNow+"");
				
				//重新跳转customerM.jsp
				request.getRequestDispatcher("customerM.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (request.getParameter("flag").equals("delete")) {
			
			//删除该名客户
			try {
				
				//得到要删除的id号
				int cId = Integer.parseInt((String)request.getParameter("cId"));
				
				//调用CustomerBeanCl
				CustomerBeanCl customerBeanCl = new CustomerBeanCl();
				
				request.setAttribute("page", "customerM");
				if(customerBeanCl.deleteById(cId)){
					//删除成功
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//伤处失败
					request.getRequestDispatcher("handleFailure.jsp").forward(request, response);;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (request.getParameter("flag").equals("add")) {
			//添加客户信息
			
			try {
				
				//得到要添加的客户信息
				String cName = request.getParameter("cName");
				String cPassword = request.getParameter("cPassword");
				String cContactWay = request.getParameter("cContactWay");
				
				//调用CustomerBeanCl
				CustomerBeanCl customerBeanCl = new CustomerBeanCl();
				
				request.setAttribute("page", "customerM");
				
				if(customerBeanCl.addCustomer(cName, cPassword, cContactWay)){
					//添加成功
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//添加失败
					request.getRequestDispatcher("handleFailure.jsp").forward(request, response);;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if (request.getParameter("flag").equals("update")) {
			//修改客户信息
			
			try {
				
				//得到要修改的客户信息
				String cId = request.getParameter("cId");
				String cName = request.getParameter("cName");
				String cPassword = request.getParameter("cPassword");
				String cContactWay = request.getParameter("cContactWay");
				
				//调用CustomerBeanCl
				CustomerBeanCl customerBeanCl = new CustomerBeanCl();
				
				request.setAttribute("page", "customerM");
				
				if(customerBeanCl.updateCustomer(cId, cName, cPassword, cContactWay)) {
					//修改成功
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//修改失败
					request.getRequestDispatcher("handleFailure.jsp").forward(request, response);;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
