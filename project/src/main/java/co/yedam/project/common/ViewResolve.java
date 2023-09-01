package co.yedam.project.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolve {
	public static void views(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
	    String jspPage = "WEB-INF/views/" + page + ".jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspPage);
		dispatcher.forward(request, response);
	}
}
