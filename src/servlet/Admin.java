package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MutterDAO;
import dao.UserDAO;
import model.Mutter;
import model.User;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_NAME = "admin";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		String forwardPath = "/WEB-INF/jsp/adminLogin.jsp";
		if(user != null && user.getName().equals("admin")) {
			forwardPath = "/WEB-INF/jsp/adminPage.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name").trim();
		String pass = request.getParameter("pass").trim();
		String forwardPath = "/WEB-INF/jsp/adminLogin.jsp";
		
		// セッションスコープの初期化
		HttpSession sessionCheck = request.getSession();
		if(sessionCheck != null) {
			sessionCheck.invalidate();
		}
		
		//入力情報に誤りがあればトップへ
		ArrayList<String> eMessage = new ArrayList<>();
		
		if((name != "" && name != null) && (pass != "" && pass != null)) {
			User user = null;
			UserDAO dao = new UserDAO();
			MutterDAO mdao = new MutterDAO();
			
			if(name.equals(ADMIN_NAME)) {
				user = dao.findUser(name, pass);				
			}
		
			if(user != null) {
				HttpSession session = request.getSession();
				List<User> userList = dao.findAll();
				List<Mutter> mutterList = mdao.findAllAdmin();				
				session.setAttribute("loginUser", user);
				session.setAttribute("userList", userList);
				session.setAttribute("mutterList", mutterList);
				forwardPath = "/WEB-INF/jsp/adminPage.jsp";
			} else {
				eMessage.add("管理者名とパスワードが一致しません");
			}
		} else {
			if(name == "" || name == null) {
				eMessage.add("管理者名を入力してください");
			}
			if(pass == "" || pass == null) {
				eMessage.add("パスワードを入力してください");
			}
		}

		request.setAttribute("eMessage", eMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
