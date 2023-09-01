package co.yedam.project.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.project.common.ViewResolve;
import co.yedam.project.member.service.MemberService;
import co.yedam.project.member.service.MemberVO;
import co.yedam.project.member.serviceImpl.MemberServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Service 생성(DAO)
		MemberService dao = new MemberServiceImpl();
		// MemberVO
		MemberVO vo = new MemberVO();
		// vo 넘어온 값을 담고
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPassword(request.getParameter("memberPassword"));
		// dao 호출 : vo = dao.memberSelect();
		vo = dao.memberSelect(vo);
		// 결과 처리
		if(vo != null) {
			request.setAttribute("message", "로그인 성공!!!");
		} else {
			request.setAttribute("message", "로그인 실패!!!");
		}
		
		//String id = request.getParameter("memberId"); //vo.setMemberId();
		//String password = request.getParameter("memberPassword"); //vo.setMemberPassword();
		//request.setAttribute("id", id);
		//request.setAttribute("password", password);
		
		
		// 보여줄 페이지에 값을 전달
		String page ="member/membermessage";
		ViewResolve.views(request, response, page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
