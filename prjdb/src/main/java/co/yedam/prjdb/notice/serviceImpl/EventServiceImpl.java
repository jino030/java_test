package co.yedam.prjdb.notice.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.common.DataSource;
import co.yedam.prjdb.notice.map.EventMapper;
import co.yedam.prjdb.notice.service.EventService;
import co.yedam.prjdb.notice.service.EventVO;

public class EventServiceImpl implements EventService{

	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private EventMapper map = sqlSession.getMapper(EventMapper.class);
	
	@Override
	public List<EventVO> eventSelectList() {
		return map.eventSelectList();
	}

}
