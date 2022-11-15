package servlet;

import java.io.IOException;
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
import model.LoginLogic;
import model.Mutter;
import model.User;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String forwardPath = "/WEB-INF/jsp/adminPage.jsp";
		
		// セッションスコープの初期化
		HttpSession sessionCheck = request.getSession();
		if(sessionCheck != null) {
			sessionCheck.invalidate();
		}
		
		UserDAO dao = new UserDAO();
		List<User> userList = dao.findAll();
		
		// ユーザー情報のインスタンスを生成
		User user = new User(name, pass);
		
		LoginLogic logic = new LoginLogic();
		boolean isLogin = logic.executeAdmin(user, userList);
		
		if(isLogin) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			session.setAttribute("userList", userList);
			MutterDAO mDao = new MutterDAO();
			List<Mutter> mutterList = mDao.findAllAdmin();
			session.setAttribute("mutterList", mutterList);
		} else {
			request.setAttribute("msg", "入力情報に誤りがあります");
			forwardPath = "/WEB-INF/jsp/info.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
