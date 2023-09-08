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

import co.yedam.prjdb.notice.service.EventService;
import co.yedam.prjdb.notice.service.EventVO;
import co.yedam.prjdb.notice.serviceImpl.EventServiceImpl;

@WebServlet("/ajaxEventList.do")
public class AjaxEventList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxEventList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventService dao = new EventServiceImpl();
		List<EventVO> events = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		events = dao.eventSelectList();
		
		if(events != null) {
			String json = objectMapper.writeValueAsString(events);
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(json);
		} else {
			System.out.println("ajaxEventList.do 오류발생");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
