package co.yedam.prjdb.book.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prjdb.book.service.BookService;
import co.yedam.prjdb.book.service.BookVO;
import co.yedam.prjdb.book.serviceImpl.BookServiceImpl;

@WebServlet("/ajaxbookdelete.do")
public class AjaxBookDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxBookDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService dao = new BookServiceImpl();
		BookVO vo = new BookVO();
		
		int bookId = Integer.valueOf(request.getParameter("bid"));
		vo.setBookId(bookId);
		
		int n = dao.bookDelete(vo);
		
		if(n != 0) {
			response.getWriter().print("{\"retCode\" : \"Success\"}");
		} else {
			response.getWriter().print("{\"retCode\" : \"Fail\"}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
