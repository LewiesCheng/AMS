package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductBeanCl;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("flag").equals("fenye")){
			//分页显示制品信息（包括查询）
			try {
				
				//得到用户希望显示的pageNow
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));
				
				//调用CustomerBeanCl
				ProductBeanCl productBeanCl = new ProductBeanCl();
				
				int pageCount = 0;
				
				ArrayList<ProductBean> arrayList = new ArrayList<>();
				if(request.getParameter("search").equals("all")){
					//得到要显示的所有制品信息
					arrayList = productBeanCl.getProductByPage(pageNow, "all", null);
					//得到总页数
					pageCount = productBeanCl.getPageCount("all", null);
					
					request.setAttribute("search", "all");
					request.setAttribute("need", null);
					request.setAttribute("value", null);
				} else if (request.getParameter("search").equals("notAll")) {
					//不是默认查询所有
					if(request.getParameter("need").equals("All")){
						//查询所有制品信息
						arrayList = productBeanCl.getProductByPage(pageNow, "all", null);
						pageCount = productBeanCl.getPageCount("all", null);
						
						request.setAttribute("search", "all");
						request.setAttribute("need", null);
						request.setAttribute("value", null);
					}else{
						//根据要求查询制品信息
						arrayList = productBeanCl.getProductByPage(pageNow, request.getParameter("need"), request.getParameter("needValue"));
						pageCount = productBeanCl.getPageCount(request.getParameter("need"), request.getParameter("needValue"));
						
						request.setAttribute("search", "notAll");
						request.setAttribute("need", request.getParameter("need"));
						request.setAttribute("value", request.getParameter("needValue"));
					}
				}
				
				
				request.setAttribute("pResult", arrayList);
				request.setAttribute("pPageCount", pageCount+"");
				request.setAttribute("pPageNow", pageNow+"");
				
				//重新跳转productM.jsp
				request.getRequestDispatcher("productM.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (request.getParameter("flag").equals("delete")) {
			//删除音像制品
			try {
				
				//得到要删除的音像制品的id号
				int pId = Integer.parseInt((String)request.getParameter("pId"));
				ProductBeanCl productBeanCl = new ProductBeanCl();
				
				request.setAttribute("page", "productM");
				if(productBeanCl.deleteById(pId)){
					//删除成功
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//删除失败
					request.getRequestDispatcher("handleFailure.jsp").forward(request, response);;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (request.getParameter("flag").equals("add")) {
			//添加音像制品
			
			try {
				
				//得到要添加的音像制品的信息
				String pType = request.getParameter("pType");
				String pName = request.getParameter("pName");
				String pInventory = request.getParameter("pInventory");
				String pPriceOneDay = request.getParameter("pPriceOneDay");
				
				ProductBeanCl productBeanCl = new ProductBeanCl();
				
				request.setAttribute("page", "productM");
				if(productBeanCl.addProduct(pType, pName, pInventory, pPriceOneDay)){
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
			//修改音像制品信息
			
			try {
				
				//得到要添加的音像制品的信息
				String pId = request.getParameter("pId");
				String pType = request.getParameter("pType");
				String pName = request.getParameter("pName");
				String pInventory = request.getParameter("pInventory");
				String pPriceOneDay = request.getParameter("pPriceOneDay");
				
				ProductBeanCl productBeanCl = new ProductBeanCl();
				
				request.setAttribute("page", "productM");
				if(productBeanCl.updateProduct(pId, pType, pName, pInventory, pPriceOneDay)){
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
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
