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

import dao.GoodDAO;
import model.GetMutterListLogic;
import model.Mutter;
import model.User;

/**
 * Servlet implementation class Good
 */
@WebServlet("/Good")
public class Good extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		int user_id = user.getId();
		
		String forwardPath = "/WEB-INF/jsp/main.jsp";
		int mutter_id = Integer.parseInt(request.getParameter("mutter_id"));
		GoodDAO dao = new GoodDAO();
		dao.checkGoodList(user_id, mutter_id);
		
		// つぶやきリストを取得してリクエストスコープに保存
		GetMutterListLogic gmll = new GetMutterListLogic();
		List<Mutter> mutterList = gmll.execute();
		request.setAttribute("mutterList", mutterList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}
