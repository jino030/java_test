package co.yedam.prjdb.notice.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

@WebServlet("/ajaxReplyList.do")
public class AjaxReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxReplyList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReplyService dao = new ReplyServiceImpl();
		List<ReplyVO> list = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()); // json 형태의 데이터로 반환 =>
																								// jackson 라이브러리 사용

		// 이해가 안가...
		String noticeId = request.getParameter("noticeId");
		// 추가.
		String param = request.getParameter("param");

		if (param == null) {
			list = dao.replySelectList(Integer.parseInt(noticeId));
			String json = objectMapper.writeValueAsString(list);

			response.setContentType("text/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		} else if (param == "jquery") {
			// {"data" : [["값"], [], ...., []]}
			list = dao.replySelectList(Integer.parseInt(noticeId));
			String json = "{\"data\": [";
			int cnt = 0;
			for (ReplyVO vo : list) {
				json += "[" 
					  + "\"" + vo.getNoticeId() + "\"," 
					  + "\"" + vo.getReply() + "\"," 
					  + "\"" + vo.getReplyer() + "\"," 
					  + "\"" + vo.getReplyDate() + "\"" 
					  + "]";
				if(++cnt != list.size()) { // 마지막 사이즈가 아닐때만 ,를 추가
					json += ",";
				}
			}
			json += "]}";
			
			response.setContentType("text/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
			
		} else {
			list = dao.replySelectList(Integer.parseInt(noticeId));
			String json = "{\"data\": [";
			int cnt = 0;
			
			
			json += "]}";
			
			Map<String, Object> map = new HashMap<>();
			map.put("data", list);
			
			json = objectMapper.writeValueAsString(map);

			response.setContentType("text/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
