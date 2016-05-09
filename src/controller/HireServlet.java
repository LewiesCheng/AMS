package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HireBean;
import model.HireBeanCl;

/**
 * Servlet implementation class HireServlet
 */
@WebServlet("/HireServlet")
public class HireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("flag").equals("fenye")){
			//��Ҫ���ѯ������Ϣ
			try {
				
				//�õ��û�ϣ����ʾ��pageNow
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));
				
				//����hireBeanCl
				HireBeanCl hireBeanCl = new HireBeanCl();
				
				int pageCount = 0;
				
				ArrayList<HireBean> arrayList = new ArrayList<>();
				if(request.getParameter("search").equals("all")){
					
					arrayList = hireBeanCl.getHireByPage(pageNow, "all", null);
					pageCount = hireBeanCl.getPageCount("all", null);
					
					request.setAttribute("search", "all");
					request.setAttribute("need", null);
					request.setAttribute("value", null);
				} else if (request.getParameter("search").equals("notAll")) {
					
					if(request.getParameter("need").equals("All")){
						
						arrayList = hireBeanCl.getHireByPage(pageNow, "all", null);
						pageCount = hireBeanCl.getPageCount("all", null);
						
						request.setAttribute("search", "all");
						request.setAttribute("need", null);
						request.setAttribute("value", null);
					}else{
						
						arrayList = hireBeanCl.getHireByPage(pageNow, request.getParameter("need"), request.getParameter("needValue"));
						pageCount = hireBeanCl.getPageCount(request.getParameter("need"), request.getParameter("needValue"));
						
						request.setAttribute("search", "notAll");
						request.setAttribute("need", request.getParameter("need"));
						request.setAttribute("value", request.getParameter("needValue"));
					}
				}
				
				
				request.setAttribute("hResult", arrayList);
				request.setAttribute("hPageCount", pageCount+"");
				request.setAttribute("hPageNow", pageNow+"");
				
				//������תhireData.jsp
				request.getRequestDispatcher("hireData.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (request.getParameter("flag").equals("verify")) {
			//��֤�ͻ���Ϣ
			try {
				//�õ��ǽ��or�黹
				String begAndRet = request.getParameter("begAndRet");
				
				String cName = request.getParameter("cName");
				String cPassword = request.getParameter("cPassword");
				
				HireBeanCl hireBeanCl =new HireBeanCl();
				if(hireBeanCl.checkCustomer(cName, cPassword)){
					//��֤�ɹ����ÿͻ�����
					if(begAndRet.equals("begin")){
						//����
						request.setAttribute("cId", hireBeanCl.getCId(cName));
						request.setAttribute("todayDate", hireBeanCl.getTodayDate());
						request.setAttribute("flag", begAndRet);
						
						request.getRequestDispatcher("hireBeginAndReturn.jsp").forward(request, response);
					} else if (begAndRet.equals("return")) {
						//�黹
						ArrayList<HireBean> arrayList = hireBeanCl.getNoReturnHireByPage(1, hireBeanCl.getCId(cName)+"");
						int pageCount = hireBeanCl.getPageCountOfNoReturnByCId(hireBeanCl.getCId(cName)+"");
						
						request.setAttribute("cId", hireBeanCl.getCId(cName)+"");
						request.setAttribute("todayDate", hireBeanCl.getTodayDate());
						request.setAttribute("cName", cName);
						request.setAttribute("hResult", arrayList);
						request.setAttribute("hPageCount", pageCount+"");
						request.setAttribute("hPageNow", "1");
						request.setAttribute("flag", begAndRet);
						request.getRequestDispatcher("hireBeginAndReturn.jsp").forward(request, response);
					}
				} else {
					//��֤���ɹ������ÿͻ������ڣ���Ҫע��
					request.getRequestDispatcher("handleFailure.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} else if (request.getParameter("flag").equals("hireBegin")) {
			//��ӳ�����Ϣ
			
			try {
				
				//�õ�Ҫ��ӵ�������Ʒ����Ϣ
				String hCustomerId = request.getParameter("hCustomerId");
				String hProductId = request.getParameter("hProductId");
				String hNumber = request.getParameter("hNumber");
				String hBeginDay = request.getParameter("hBeginDay");
				String hDeposit = request.getParameter("hDeposit");
				
				HireBeanCl hireBeanCl = new HireBeanCl();
				
				request.setAttribute("flag", "begin");
				request.setAttribute("page", "hireM");
				if(hireBeanCl.addHire(hCustomerId, hProductId, hNumber, hBeginDay, hDeposit)){
					//��ӳɹ�
					request.getRequestDispatcher("handleSuccess.jsp").forward(request, response);
				} else {
					//���ʧ��
					request.getRequestDispatcher("handleFailure.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		else if (request.getParameter("flag").equals("hireReturn")) {
			//�޸�������Ʒ��Ϣ,���黹��Ʒ
			
			try {
				
				String cId = request .getParameter("cId");
				//�õ��û�ϣ����ʾ��pageNow
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));
				
				//����hireBeanCl
				HireBeanCl hireBeanCl = new HireBeanCl();
				
				ArrayList<HireBean> arrayList = hireBeanCl.getNoReturnHireByPage(pageNow, cId);
				int pageCount = hireBeanCl.getPageCountOfNoReturnByCId(cId);
				request.setAttribute("cId", cId);
				request.setAttribute("cName", request.getParameter("cName"));
				request.setAttribute("todayDate", hireBeanCl.getTodayDate());
				request.setAttribute("hResult", arrayList);
				request.setAttribute("hPageCount", pageCount+"");
				request.setAttribute("hPageNow", pageNow+"");
				request.setAttribute("flag", "return");
				
				request.getRequestDispatcher("hireBeginAndReturn.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (request.getParameter("flag").equals("hireReturnMoney")) {
			try {
				
				String hId = request.getParameter("hId");
				String hReturnDay = request.getParameter("hReturnDay");
				//����hireBeanCl
				HireBeanCl hireBeanCl = new HireBeanCl();
				int hDeposit = hireBeanCl.getHireDepositByhId(hId);
				int hTotalPrice = hireBeanCl.getReturnMoney(hId, hReturnDay);
				request.setAttribute("hId", hId);
				request.setAttribute("hReturnDay", hReturnDay);
				request.setAttribute("hDeposit", hDeposit);
				request.setAttribute("hTotalPrice", hTotalPrice);
				
				request.getRequestDispatcher("returnMoney.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (request.getParameter("flag").equals("hireReturnAfter")) {
			try {
				
				String hId = request.getParameter("hId");
				String hReturnDay = request.getParameter("hReturnDay");
				String hTotalPrice = request.getParameter("hTotalPrice");
				//����hireBeanCl
				HireBeanCl hireBeanCl = new HireBeanCl();
				
				request.setAttribute("flag", "return");
				request.setAttribute("page", "hireM");
				if(hireBeanCl.hireReturn(hId, hReturnDay, hTotalPrice)){
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
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
