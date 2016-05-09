package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StatisticsCl;

/**
 * Servlet implementation class StatisticsServlet
 */
@WebServlet("/StatisticsServlet")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StatisticsCl statisticsCl = new StatisticsCl();
		request.setAttribute("customerNumber", statisticsCl.getCustomerNumber());
		request.setAttribute("audioNumber", statisticsCl.getAudioNumber());
		request.setAttribute("totalProfits", statisticsCl.getTotalProfits());
		request.setAttribute("remainDeposit", statisticsCl.getRemainDeposit());
		request.setAttribute("todayHire", statisticsCl.getTodayHire());
		request.setAttribute("todayReturn", statisticsCl.getTodayReturn());
		request.setAttribute("todayReturnDeposit", statisticsCl.getTodayReturn()*50);
		request.setAttribute("todayProfits", statisticsCl.getTodayProfits());
		request.getRequestDispatcher("statistics.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
