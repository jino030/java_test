package co.yedam.prjdb.notice.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.common.DataSource;
import co.yedam.prjdb.notice.map.NoticeMapper;
import co.yedam.prjdb.notice.service.NoticeService;
import co.yedam.prjdb.notice.service.NoticeVO;

public class NoticeServiceImpl implements NoticeService {

	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private NoticeMapper map = sqlSession.getMapper(NoticeMapper.class);
	
	@Override
	public List<NoticeVO> noticeSelectList() {
		return map.noticeSelectList();
		
	}

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {
		map.noticeHitUpdate(vo.getNoticeId()); //조회수를 증가 시킨다.
		return map.noticeSelect(vo);
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		return map.noticeInsert(vo);
	}

	@Override
	public int noticeUdpate(NoticeVO vo) {
		return map.noticeUdpate(vo);
	}

	@Override
	public int noticeDelete(NoticeVO vo) {
		return map.noticeDelete(vo);
	}

	@Override
	public void noticeHitUpdate(int id) {
		//map.noticeHitUpdate(id);
	}

	@Override
	public List<NoticeVO> noticeSelectList(String key, String val) {
		return map.noticeSelectList(key, val);
	}

}
