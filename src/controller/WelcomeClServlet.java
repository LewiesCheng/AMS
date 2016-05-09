package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustomerBean;
import model.CustomerBeanCl;
import model.ProductBean;
import model.ProductBeanCl;

/**
 * Servlet implementation class WelcomeClServlet
 */
@WebServlet("/WelcomeClServlet")
public class WelcomeClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {			
			
			String requestPage = (String)(request.getParameter("requestPage"));
			if(requestPage.equals("customerM")) {
				//客户管理
				CustomerBeanCl customerBeanCl = new CustomerBeanCl();
				ArrayList<CustomerBean> cArrayList= customerBeanCl.getCustomerByPage(1, "all", null);
				int cPageCount = customerBeanCl.getPageCount();
				
				request.setAttribute("cResult", cArrayList);
				request.setAttribute("cPageCount", cPageCount);
				request.setAttribute("cPageNow", "1");
				request.setAttribute("search", "all");
				
				request.getRequestDispatcher("customerM.jsp").forward(request, response);
				
			} else if (requestPage.equals("productM")) {
				//音像制品管理
				ProductBeanCl productBeanCl = new ProductBeanCl();
				ArrayList<ProductBean> pArrayList = productBeanCl.getProductByPage(1,"all",null);
				int pPageCount = productBeanCl.getPageCount("all", null);
				
				request.setAttribute("pResult", pArrayList);
				request.setAttribute("pPageCount", pPageCount);
				request.setAttribute("pPageNow", "1");
				request.setAttribute("search", "all");
				
				request.getRequestDispatcher("productM.jsp").forward(request, response);
				
			} else if (requestPage.equals("hireM")) {
				//租赁管理
				request.getRequestDispatcher("hireM.jsp").forward(request, response);
			} else if (requestPage.equals("quit")) {
				//安全退出
				HttpSession httpSession = request.getSession(true);
				httpSession.removeAttribute("adminName");
				response.sendRedirect("login.jsp");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
