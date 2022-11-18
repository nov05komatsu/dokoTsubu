package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name").trim();
		String pass = request.getParameter("pass").trim();
		String forwardPath = "/index.jsp";
		
		// セッションスコープの初期化
		HttpSession sessionCheck = request.getSession();
		if(sessionCheck != null) {
			sessionCheck.invalidate();
		}
		
		//入力情報に誤りがあればトップへ
		ArrayList<String> eMessage = new ArrayList<>();
		
		if((name != "" && name != null) && (pass != "" && pass != null)) {
			UserDAO dao = new UserDAO();
			User user = dao.findUser(name, pass);
		
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
				forwardPath = "/WEB-INF/jsp/loginResult.jsp";
			} else {
				eMessage.add("ユーザー名とパスワードが一致しません");
			}
		} else {
			if(name == "" || name == null) {
				eMessage.add("ユーザー名を入力してください");
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
