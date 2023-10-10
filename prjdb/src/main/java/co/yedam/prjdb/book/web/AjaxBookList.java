package co.yedam.prjdb.book.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.prjdb.book.service.BookService;
import co.yedam.prjdb.book.service.BookVO;
import co.yedam.prjdb.book.serviceImpl.BookServiceImpl;

@WebServlet("/ajaxbooklist.do")
public class AjaxBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxBookList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService dao = new BookServiceImpl();
		List<BookVO> books = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		books = dao.bookSelectList();
		String json = objectMapper.writeValueAsString(books);
		response.setContentType("text/json; charset=UTF-8");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
