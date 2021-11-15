package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Admin;
import model.bean.People;
import model.bo.AdminBo;
import model.bo.PeopleBo;


@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CheckLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminBo adminbo = new AdminBo();
		PeopleBo ppbo = new PeopleBo();

		
		if(adminbo.isExist(username, password)) {
			People people = ppbo.getDetailById(adminbo.getAdmin(username, password).getId());
			request.getSession().setAttribute("people",people);
			RequestDispatcher dispatcher = request.getRequestDispatcher("detail.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
