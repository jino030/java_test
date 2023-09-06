package co.yedam.prjdb.notice.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.common.DataSource;
import co.yedam.prjdb.notice.map.ReplyMapper;
import co.yedam.prjdb.notice.service.ReplyService;
import co.yedam.prjdb.notice.service.ReplyVO;

public class ReplyServiceImpl implements ReplyService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private ReplyMapper map = sqlSession.getMapper(ReplyMapper.class);

	@Override
	public List<ReplyVO> replySelectList(int noticeId) {
		return map.replySelectList(noticeId);
	}

	@Override
	public ReplyVO replySelect(int replyId) {
		return map.replySelect(replyId);
	}

	@Override
	public int replyInsert(ReplyVO vo) {
		return map.replyInsert(vo);
	}

	@Override
	public int replyUpdate(ReplyVO vo) {
		return map.replyUpdate(vo);
	}

	@Override
	public int replyDelete(ReplyVO vo) {
		return map.replyDelete(vo);
	}

}
