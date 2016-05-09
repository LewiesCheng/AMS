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
			
			//��ҳ��ʾ�ͻ���������Ϣ
			try {
				
				//�õ��û�ϣ����ʾ��pageNow
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));
				
				//����CustomerBeanCl
				CustomerBeanCl customerBeanCl = new CustomerBeanCl();
				//ҳ��
				int pageCount = 1;
				//��ѯ��Ҫ��ʾ�Ŀͻ���Ĭ����ʾ���пͻ���
				ArrayList<CustomerBean> arrayList = new ArrayList<>();
				if (request.getParameter("search").equals("all")) {
					
					//��ʾ���пͻ�
					arrayList= customerBeanCl.getCustomerByPage(pageNow, "all", "null");
					//ҳ��
					pageCount = customerBeanCl.getPageCount();
					request.setAttribute("search", "all");
					
				} else if (request.getParameter("search").equals("notAll")) {
					if(request.getParameter("need").equals("All")){
						//��ʾ���пͻ�
						arrayList= customerBeanCl.getCustomerByPage(pageNow, "all", "null");
						//ҳ��
						pageCount = customerBeanCl.getPageCount();
						request.setAttribute("search", "all");
						
					} else {
						//��Ҫ���ѯ�ͻ�
						arrayList= customerBeanCl.getCustomerByPage(pageNow, request.getParameter("need"), request.getParameter("needValue"));
					}
				} 
				
				
				
				request.setAttribute("cResult", arrayList);
				request.setAttribute("cPageCount", pageCount+"");
				request.setAttribute("cPageNow", pageNow+"");
				
				//������תcustomerM.jsp
				request.getRequestDispatcher("customerM.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (request.getParameter("flag").equals("delete")) {
			
			//ɾ�������ͻ�
			try {
				
				//�õ�Ҫɾ����id��
				int cId = Integer.parseInt((String)request.getParameter("cId"));
				
				//����CustomerBeanCl
				CustomerBeanCl customerBeanCl = new CustomerBeanCl();
				
				request.setAttribute("page", "customerM");
				if(customerBeanCl.deleteById(cId)){
					//ɾ���ɹ�
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//�˴�ʧ��
					request.getRequestDispatcher("handleFailure.jsp").forward(request, response);;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (request.getParameter("flag").equals("add")) {
			//��ӿͻ���Ϣ
			
			try {
				
				//�õ�Ҫ��ӵĿͻ���Ϣ
				String cName = request.getParameter("cName");
				String cPassword = request.getParameter("cPassword");
				String cContactWay = request.getParameter("cContactWay");
				
				//����CustomerBeanCl
				CustomerBeanCl customerBeanCl = new CustomerBeanCl();
				
				request.setAttribute("page", "customerM");
				
				if(customerBeanCl.addCustomer(cName, cPassword, cContactWay)){
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
			//�޸Ŀͻ���Ϣ
			
			try {
				
				//�õ�Ҫ�޸ĵĿͻ���Ϣ
				String cId = request.getParameter("cId");
				String cName = request.getParameter("cName");
				String cPassword = request.getParameter("cPassword");
				String cContactWay = request.getParameter("cContactWay");
				
				//����CustomerBeanCl
				CustomerBeanCl customerBeanCl = new CustomerBeanCl();
				
				request.setAttribute("page", "customerM");
				
				if(customerBeanCl.updateCustomer(cId, cName, cPassword, cContactWay)) {
					//�޸ĳɹ�
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//�޸�ʧ��
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
