package co.yedam.prjdb.notice.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.yedam.prjdb.notice.service.ReplyService;
import co.yedam.prjdb.notice.service.ReplyVO;
import co.yedam.prjdb.notice.serviceImpl.ReplyServiceImpl;

@WebServlet("/ajaxReplySearch.do")
public class AjaxReplySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxReplySearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rid = request.getParameter("rid");
		ReplyService dao = new ReplyServiceImpl();
		
		ReplyVO vo = dao.replySelect(Integer.parseInt(rid));
		
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()); //json 형태의 데이터로 반환 => jackson 라이브러리 사용
		String json = objectMapper.writeValueAsString(vo);
		
		response.setContentType("text/json; charset=UTF-8");
		response.getWriter().append(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
