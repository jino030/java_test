package co.yedam.prjdb.notice.map;

import java.util.List;

import co.yedam.prjdb.notice.service.EventVO;

public interface EventMapper {
	List<EventVO> eventSelectList();
}
