package co.yedam.prjdb.notice.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prjdb.notice.service.ReplyService;
import co.yedam.prjdb.notice.service.ReplyVO;
import co.yedam.prjdb.notice.serviceImpl.ReplyServiceImpl;

@WebServlet("/ajaxReplyDelete.do")
public class AjaxReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxReplyDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyService dao = new ReplyServiceImpl();
		ReplyVO vo = new ReplyVO();
		
		String replyId = request.getParameter("rid");
		vo.setReplyId(Integer.parseInt(replyId));
		
		int n = dao.replyDelete(vo);
		
		if(n != 0) {
			// json 코드를 넘겨준다 
			// {"retCode" : "Success"}
			response.getWriter().print("{\"retCode\" : \"Success\"}");
		} else {
			// {"retCode" : "Fail"}
			response.getWriter().print("{\"retCode\" : \"Fail\"}");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
