package co.yedam.prjdb.book.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.yedam.prjdb.book.service.BookService;
import co.yedam.prjdb.book.service.BookVO;
import co.yedam.prjdb.book.serviceImpl.BookServiceImpl;

@WebServlet("/ajaxbookadd.do")
public class AjaxBookAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxBookAdd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService dao = new BookServiceImpl();
		BookVO vo = new BookVO();
		
		String bookCode = request.getParameter("bookCode");
		String bookName = request.getParameter("bookName");
		String bookAuthor = request.getParameter("bookAuthor");
		String bookPublisher = request.getParameter("bookPublisher");
		int bookPrice = Integer.valueOf(request.getParameter("bookPrice"));
		
		vo.setBookCode(bookCode);
		vo.setBookName(bookName);
		vo.setBookAuthor(bookAuthor);
		vo.setBookPublisher(bookPublisher);
		vo.setBookPrice(bookPrice);
		
		Map<String, Object> resultMap = new HashMap<>(); //key, val 형식으로 데이터를 담을 수 있음.
		int n = dao.bookInsert(vo);
		
		if(n != 0) {
			resultMap.put("retCode", "Success");
			resultMap.put("book", vo);
		} else {
			resultMap.put("retCode", "Fail");
		}
		
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()); //json 형태의 데이터로 반환 => jackson 라이브러리 사용
		String json = objectMapper.writeValueAsString(resultMap);
		
		response.setContentType("text/json; charset=UTF-8");
		response.getWriter().print(json); // 출력 스트림..
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
