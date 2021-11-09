package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.CheckLoginBO;
import model.Bean.Account;

@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Account a = CheckLoginBO.CheckLogin(id,pw);
		if (a==null) {
			//Quay lai trang login
			response.sendRedirect("Login.jsp");
		} else {
			//Hien thi trang myhome
			request.getSession().setAttribute("acount", a);
			response.sendRedirect("MyHome.jsp");
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
