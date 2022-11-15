package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MutterDAO;
import model.Mutter;

@WebServlet("/RemoveMutterAdmin")
public class RemoveMutterAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardPath = "/WEB-INF/jsp/removeMutterAdmin.jsp";
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findAll();
		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ary = request.getParameterValues("list");
		MutterDAO dao = new MutterDAO();
		dao.removeMutter(ary);		
		List<Mutter> mutterList = dao.findAll();
		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp");
		dispatcher.forward(request, response);
	}
}
