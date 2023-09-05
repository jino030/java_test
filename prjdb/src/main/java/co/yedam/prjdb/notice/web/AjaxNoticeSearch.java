package co.yedam.prjdb.notice.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.yedam.prjdb.notice.service.NoticeService;
import co.yedam.prjdb.notice.service.NoticeVO;
import co.yedam.prjdb.notice.serviceImpl.NoticeServiceImpl;

@WebServlet("/ajaxNoticeSearch.do")
public class AjaxNoticeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxNoticeSearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService dao = new NoticeServiceImpl();
		List<NoticeVO> notices = new ArrayList<NoticeVO>();
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()); //json 형태의 데이터로 반환 => jackson 라이브러리 사용
		
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		System.out.println("key: " + key + ", val: " + val);
		
		notices = dao.noticeSelectList(key, val);
		String list = objectMapper.writeValueAsString(notices); //list형태의 데이터가 json형태로 바뀐다.
		
		response.setContentType("text/html; charset=UTF-8"); //없어도 될수 있으나 안될때를 대비해서 넣어둠
		response.getWriter().append(list); //ajax를 return시킬때..
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
