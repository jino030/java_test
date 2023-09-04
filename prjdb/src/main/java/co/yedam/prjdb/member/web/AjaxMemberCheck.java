package co.yedam.prjdb.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prjdb.member.service.MemberService;
import co.yedam.prjdb.member.service.MemberVO;
import co.yedam.prjdb.member.serviceImpl.MemberServiceImpl;

@WebServlet("/aJaxmembercheck.do")
public class AjaxMemberCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AjaxMemberCheck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		
		vo.setMemberId(request.getParameter("memberId"));
		vo = dao.memberSelect(vo); //이미 존재하는 아이디이면 vo에 결과값이 담기게된다.
		
		String str = "Yes"; // 사용가능한 아이디
		if(vo != null) {
			str = "No"; // 이미 존재하는 아이디 입니다.
		}
		
		// ajax로 값 처리(호출한 페이지로 결과 리턴 : response)
		response.setContentType("text/html; charset=UTF-8"); //없어도 될수 있으나 안될때를 대비해서 넣어둠
		response.getWriter().append(str);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
