package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginCl;



/**
 * Servlet implementation class LoginClServlet
 */

public class LoginClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到用户名和密码
		String name = request.getParameter("username");
		
		String password = request.getParameter("passwd");
			
		LoginCl loginCl = new LoginCl();
		
		if(loginCl.checkUser(name, password)){
			//合法用户
			
			//将登录的理员名存放到session中
			request.getSession().setAttribute("adminName", name);
			
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}
		else{
			//不合法用户
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
