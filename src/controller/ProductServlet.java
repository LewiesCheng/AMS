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
			//��ҳ��ʾ��Ʒ��Ϣ��������ѯ��
			try {
				
				//�õ��û�ϣ����ʾ��pageNow
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));
				
				//����CustomerBeanCl
				ProductBeanCl productBeanCl = new ProductBeanCl();
				
				int pageCount = 0;
				
				ArrayList<ProductBean> arrayList = new ArrayList<>();
				if(request.getParameter("search").equals("all")){
					//�õ�Ҫ��ʾ��������Ʒ��Ϣ
					arrayList = productBeanCl.getProductByPage(pageNow, "all", null);
					//�õ���ҳ��
					pageCount = productBeanCl.getPageCount("all", null);
					
					request.setAttribute("search", "all");
					request.setAttribute("need", null);
					request.setAttribute("value", null);
				} else if (request.getParameter("search").equals("notAll")) {
					//����Ĭ�ϲ�ѯ����
					if(request.getParameter("need").equals("All")){
						//��ѯ������Ʒ��Ϣ
						arrayList = productBeanCl.getProductByPage(pageNow, "all", null);
						pageCount = productBeanCl.getPageCount("all", null);
						
						request.setAttribute("search", "all");
						request.setAttribute("need", null);
						request.setAttribute("value", null);
					}else{
						//����Ҫ���ѯ��Ʒ��Ϣ
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
				
				//������תproductM.jsp
				request.getRequestDispatcher("productM.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (request.getParameter("flag").equals("delete")) {
			//ɾ��������Ʒ
			try {
				
				//�õ�Ҫɾ����������Ʒ��id��
				int pId = Integer.parseInt((String)request.getParameter("pId"));
				ProductBeanCl productBeanCl = new ProductBeanCl();
				
				request.setAttribute("page", "productM");
				if(productBeanCl.deleteById(pId)){
					//ɾ���ɹ�
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//ɾ��ʧ��
					request.getRequestDispatcher("handleFailure.jsp").forward(request, response);;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (request.getParameter("flag").equals("add")) {
			//���������Ʒ
			
			try {
				
				//�õ�Ҫ��ӵ�������Ʒ����Ϣ
				String pType = request.getParameter("pType");
				String pName = request.getParameter("pName");
				String pInventory = request.getParameter("pInventory");
				String pPriceOneDay = request.getParameter("pPriceOneDay");
				
				ProductBeanCl productBeanCl = new ProductBeanCl();
				
				request.setAttribute("page", "productM");
				if(productBeanCl.addProduct(pType, pName, pInventory, pPriceOneDay)){
					//��ӳɹ�
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//���ʧ��
					request.getRequestDispatcher("handleFailure.jsp").forward(request, response);;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (request.getParameter("flag").equals("update")) {
			//�޸�������Ʒ��Ϣ
			
			try {
				
				//�õ�Ҫ��ӵ�������Ʒ����Ϣ
				String pId = request.getParameter("pId");
				String pType = request.getParameter("pType");
				String pName = request.getParameter("pName");
				String pInventory = request.getParameter("pInventory");
				String pPriceOneDay = request.getParameter("pPriceOneDay");
				
				ProductBeanCl productBeanCl = new ProductBeanCl();
				
				request.setAttribute("page", "productM");
				if(productBeanCl.updateProduct(pId, pType, pName, pInventory, pPriceOneDay)){
					//��ӳɹ�
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//���ʧ��
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
