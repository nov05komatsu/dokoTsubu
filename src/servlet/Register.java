package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.RegisterLogic;
import model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		UserDAO dao = new UserDAO();		
		List<User> userList = dao.findAll();
		
		RegisterLogic rl = new RegisterLogic();
		if(!rl.execute(name, userList)) {
			request.setAttribute("msg", "そのユーザー名はすでに使用されています。");
		} else {
			User user = new User(name, pass);
			if(dao.create(user)) {
				request.setAttribute("msg", "登録が完了しました。");				
			}
			userList = dao.findAll();
		}
		
		RequestDispatcher dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/info.jsp");
		dispathcer.forward(request, response);
		}
}
