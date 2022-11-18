package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MutterDAO;

/**
 * Servlet implementation class Good
 */
@WebServlet("/Good")
public class Good extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Good() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mutterId = Integer.parseInt(request.getParameter("mutterId"));
		int good = Integer.parseInt(request.getParameter("good"));
		
		MutterDAO dao = new MutterDAO();
		good = dao.goodSum(mutterId, good);
		
		PrintWriter out = response.getWriter();
		out.print(good);
		out.close();
		
		
		
		return;
	}

}
