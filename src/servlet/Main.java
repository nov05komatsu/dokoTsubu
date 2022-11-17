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

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//つぶやきリストを取得してリクエストスコープに保存
		GetMutterListLogic gmll = new GetMutterListLogic();
		List<Mutter> mutterList = gmll.execute();
		request.setAttribute("mutterList", mutterList);
		
		// ログイン状態の確認
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		// 取得したUserインスタンスをチェック
		// ログイン情報が入っていなければ、ログイン画面へリダイレクト
		if(loginUser == null) {
			response.sendRedirect("/dokoTsubu/");
		}
		// ログイン情報が入っていれば、メイン画面にフォワードする
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// postされた値を使う準備
		request.setCharacterEncoding("UTF-8");
		// trim()しないと半角スペースだけのつぶやきも通る
		String text = request.getParameter("text").trim();
		
		HttpSession session = request.getSession();
		// 入力値のチェックと処理
		if(text != null && text.length() != 0) {
			// セッションスコープを使うのでHttpSessionインスタンスを取得
			// セッションスコープ内の属性名loginUserのインスタンスを取得
			User loginUser = (User)session.getAttribute("loginUser");
			
			// つぶやき情報を保持する新たなインスタンスを生成する
			Mutter mutter = new Mutter(loginUser.getName(), text);
			// つぶやきを投稿する処理モデルのインスタンスを生成する
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			// 処理の実行、リストの先頭につぶやき情報インスタンスが入る
			postMutterLogic.execute(mutter);
		} else {
			//エラーメッセージをリクエストスコープに保存する
			session.setAttribute("errorMsg", "つぶやきが入力されていません");
			//属性名errorMsgのString型インスタンスが保存された状態
		}
		
		// つぶやきリストを取得してリクエストスコープに保存
		GetMutterListLogic gmll = new GetMutterListLogic();
		List<Mutter> mutterList = gmll.execute();
		session.setAttribute("mutterList", mutterList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}