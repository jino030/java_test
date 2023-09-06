package co.yedam.prjdb.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.notice.map.ReplyMapper;
import co.yedam.prjdb.notice.service.ReplyVO;

public class MainExe {
	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		ReplyMapper map = sqlSession.getMapper(ReplyMapper.class);
		
		ReplyVO rvo = new ReplyVO();
		
		rvo.setReplyId(8);
		System.out.println(map.replySelect(rvo.getReplyId()));
		
		List<ReplyVO> lists =  map.replySelectList(1);
		
		for(ReplyVO vo : lists) {
			System.out.println(vo.toString());
		}
	}
}
