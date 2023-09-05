package co.yedam.prjdb.notice.web;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.prjdb.common.ThumbNail;
import co.yedam.prjdb.common.ViewResolve;
import co.yedam.prjdb.notice.service.NoticeService;
import co.yedam.prjdb.notice.service.NoticeVO;
import co.yedam.prjdb.notice.serviceImpl.NoticeServiceImpl;

@WebServlet("/noticewrite.do")
public class NoticeWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일 업로드 처리(Notice)
		ThumbNail thumbNail = new ThumbNail(); //썸네일 Class
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		
		String saveDir = getServletContext().getRealPath("attech/notice"); //getServletContext() : prjdb / getRealPath() : 실제 경로 / 운영서버에 올릴때는 안쓴다..
		int maxSize = 1024 * 1024 * 100; //100M byte
		MultipartRequest multi = new MultipartRequest( //multipart/form-data로 오는 것은 전부 MultipartRequest로 받아야함.
				request, 
				saveDir,
				maxSize,
				"utf-8",
				new DefaultFileRenamePolicy() //파일명이 동일할때 자동으로 (1),(2) 넘버링 붙여서 저장하는 기능.
				);
		
		String imgFileName = multi.getOriginalFileName("imgfile"); //원본파일명
		String realImg = multi.getFilesystemName("imgfile"); //저장되는 파일명
		
		vo.setNoticeImage(realImg); //이미지 파일 명을 저장한다.
		
		String attech = multi.getOriginalFileName("attechfile");
		if(attech != null) {
			String attechFile = multi.getFilesystemName("attechfile");
			vo.setNoticeAttech(attechFile);
		}
		
		String fileExt = imgFileName.substring(imgFileName.lastIndexOf(".")+1); //확장자 명
		String thumb = thumbNail.makeThumbnail(saveDir + File.separator + realImg, realImg, fileExt, saveDir + File.separator);
		thumb = thumb.substring(thumb.lastIndexOf("\\")+1);
		vo.setNoticeThumb(thumb); //썸네일 생성 후 vo set
		
		
		// vo 객체에 넘어온 데이터 set
		vo.setNoticeWriter(multi.getParameter("noticeWriter"));
		vo.setNoticeDate(LocalDate.parse(multi.getParameter("noticeDate")));
		vo.setNoticeTitle(multi.getParameter("noticeTitle"));
		vo.setNoticeSubject(multi.getParameter("noticeSubject"));
		vo.setNoticeWriterName(multi.getParameter("noticeWriterName"));
		
		// db에 insert
		int n = dao.noticeInsert(vo);
		
		// insert 결과 반환
		if(n != 0) {
			response.sendRedirect("noticeselectlist.do"); // 성공했을 때는 게시글 리스트로 sendRedirect(위임)
		} else {
			request.setAttribute("message", "게시글 등록이 실패했습니다."); // 실패했을때는 noticemessage페이지 보여주기
			String page = "notice/noticemessage";
			ViewResolve.forward(request, response, page);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
