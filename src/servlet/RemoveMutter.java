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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ary = request.getParameterValues("list");
		MutterDAO dao = new MutterDAO();
		dao.removeMutter(ary);		
		List<Mutter> mutterList = dao.findAll();
		
		// 失敗の歴史
		/* removeによって配列が短くなる。次の(i)が存在しなくなる。
		for(String s : ary) {
			int i = Integer.parseInt(s);
			mutterList.remove(i);
		}*/		
		
		/* つぶやきのテキスト情報を元に処理,失敗
		for(Mutter mutter : mutterList) {
			for(String s : arrayList) {
				if(mutter.getText().equals(s)) {
					mutterList.remove(mutter);
				}
			}
		}
		*/
		// 失敗の歴史ここまで
		
		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}
