package co.yedam.prjdb.notice.map;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.yedam.prjdb.notice.service.NoticeVO;

public interface NoticeMapper {
	List<NoticeVO> noticeSelectList();
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUdpate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	
	void noticeHitUpdate(int id); // 조회수 증가
	List<NoticeVO> noticeSelectList(@Param("key") String key, @Param("val") String val); // 오버라이딩
}
