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

import dao.UserDAO;
import model.RegisterLogic;
import model.User;
import model.ValueCheckLogic;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//trim()も値チェックもしてなかったので両方空白で通ってた
		String name = request.getParameter("name").trim();
		String pass = request.getParameter("pass").trim();
		String forwardPath = "/WEB-INF/jsp/info.jsp";
		
		//入力された値をチェック、エラーがあればメッセージを受け取る
		ValueCheckLogic vcl = new ValueCheckLogic();
		ArrayList<String> eMessage = vcl.execute(name, pass);
		
		//受け取ったメッセージがあれば新規登録画面へ戻す
		if(eMessage.size() != 0) {
			request.setAttribute("eMessage", eMessage);
			forwardPath = "/WEB-INF/jsp/register.jsp";
		} else {
			// 登録ユーザーのデータを取得して名前を比較する
			// 同じ名前での登録はできないように処理
			UserDAO dao = new UserDAO();		
			List<User> userList = dao.findAll();
			User user = new User(name, pass);
			
			RegisterLogic rl = new RegisterLogic();
			if(!rl.execute(name, userList)) {
				eMessage.add("そのユーザー名はすでに使用されています");
				request.setAttribute("eMessage", eMessage);
				forwardPath = "/WEB-INF/jsp/register.jsp";
			} else if(dao.create(user)) {
				request.setAttribute("msg", "登録が完了しました。");
			}
		}

		RequestDispatcher dispathcer = request.getRequestDispatcher(forwardPath);
		dispathcer.forward(request, response);
	}
}
