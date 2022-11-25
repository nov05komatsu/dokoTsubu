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
import model.Mutter;
import model.User;

@WebServlet("/RemoveMutter")
public class RemoveMutter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//つぶやき削除ページに来た時の処理、自身のつぶやきのみを抽出して表示させたい
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardPath = "/WEB-INF/jsp/removeMutter.jsp";
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findUser(loginUser.getName());
		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);		
	}
	
	//削除ページにてつぶやきを選択し、削除するボタンを押したときの処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//<input type="checkbox" name="list" value="<c:out value="${mutter.id}"/>">
		//選択されたつぶやきの番号(mutter.id)を元に処理を実行する
		String[] ary = request.getParameterValues("list");
		MutterDAO dao = new MutterDAO();
		dao.removeMutter(ary);		
		List<Mutter> mutterList = dao.findAll();
		
		//処理後のつぶやきをリストを新たにスコープに
		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}
