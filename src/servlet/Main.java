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
import model.GetMutterListLogic;
import model.Mutter;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// ログイン状態によって処理を分ける
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		// ログイン情報の取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		// ログイン情報が入っていなければ、ログイン画面へリダイレクト
		if(loginUser == null) {
			response.sendRedirect("/dokoTsubu/");
		}
		// ログイン情報が入っていれば、メイン画面にフォワード
		else {
			//つぶやきリストを取得してリクエストスコープへ
			GetMutterListLogic gmll = new GetMutterListLogic();
			List<Mutter> mutterList = gmll.execute();
			request.setAttribute("mutterList", mutterList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	// メイン画面でつぶやきを投稿したときの処理
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		// postされた値を使う準備、filterを設定しておくのも良い？
		request.setCharacterEncoding("UTF-8");
		// trim()していなかったので空白だけのつぶやきも通っていた
		String text = request.getParameter("text").trim();
		
		// 空白を除いて1文字以上のテキストがある場合
		if(text != null && text.length() != 0) {
			//つぶやきに付与するユーザー情報の取得
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");
			
			//つぶやきインスタンス生成し、投稿する
			Mutter mutter = new Mutter(loginUser.getName(), text);
			// 直接DAOを呼び出さずにLogicを挟むべきか
			MutterDAO dao = new MutterDAO();
			dao.create(mutter);
		} else {
			//メイン画面に表示するエラーメッセージをリクエストスコープに保存しておく
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}
		
		// つぶやきリストを取得してリクエストスコープに保存
		GetMutterListLogic gmll = new GetMutterListLogic();
		List<Mutter> mutterList = gmll.execute();
		request.setAttribute("mutterList", mutterList);
		
		//再びメイン画面へ
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}