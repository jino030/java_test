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

@WebServlet("/ajaxReplyModify.do")
public class AjaxReplyModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxReplyModify() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyService dao = new ReplyServiceImpl();
		ReplyVO vo = new ReplyVO();
		
		String rid = request.getParameter("rid");
		String reply = request.getParameter("content");
		vo.setReplyId(Integer.parseInt(rid));
		vo.setReply(reply);
		
		int n = dao.replyUpdate(vo);
		Map<String, Object> resultMap = new HashMap<>(); // 성공 여부 메시지(retCode) 값도 넘기기 위해서 맵을 생성
		
		if(n != 0) {
			vo = dao.replySelect(Integer.parseInt(rid));
			resultMap.put("retCode", "Success");
			resultMap.put("data", vo);
		} else {
			resultMap.put("retCode", "Fail");
		}
		
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()); //json 형태의 데이터로 반환 => jackson 라이브러리 사용
		String json = objectMapper.writeValueAsString(resultMap); // vo만 넘기면 retCode메시지를 가져올 수 없음..
		
		response.setContentType("text/json; charset=UTF-8");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
