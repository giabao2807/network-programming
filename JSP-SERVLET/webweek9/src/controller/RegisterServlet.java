package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.People;
import model.bo.AdminBo;
import model.bo.PeopleBo;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String hobbies = request.getParameter("hobbies");
		String gender = request.getParameter("gender");
		
		AdminBo adminbo = new AdminBo();
		PeopleBo ppbo = new PeopleBo();
		if(ppbo.addPeople(id,name,hobbies,gender) && adminbo.addAdmin(username, password,id)  ) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("register.jsp");
		}
		
	}

}
