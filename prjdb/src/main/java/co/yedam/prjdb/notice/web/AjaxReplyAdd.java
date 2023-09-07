package co.yedam.prjdb.notice.web;

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

import co.yedam.prjdb.notice.service.ReplyService;
import co.yedam.prjdb.notice.service.ReplyVO;
import co.yedam.prjdb.notice.serviceImpl.ReplyServiceImpl;

@WebServlet("/ajaxReplyAdd.do")
public class AjaxReplyAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxReplyAdd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyService dao = new ReplyServiceImpl();
		ReplyVO vo = new ReplyVO();
		
		String noticeId = request.getParameter("nid");
		String reply = request.getParameter("content");
		String replyer = request.getParameter("writer");
		
		vo.setNoticeId(Integer.parseInt(noticeId));
		vo.setReply(reply);
		vo.setReplyer(replyer);
		
		Map<String, Object> resultMap = new HashMap<>(); //key, val 형식으로 데이터를 담을 수 있음.
		int n = dao.replyInsert(vo);
		
		if(n != 0) {
			resultMap.put("retCode", "Success");
			resultMap.put("data", vo);
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
