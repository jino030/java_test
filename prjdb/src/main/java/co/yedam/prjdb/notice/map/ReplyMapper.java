package co.yedam.prjdb.notice.map;

import java.util.List;

import co.yedam.prjdb.notice.service.ReplyVO;

public interface ReplyMapper {
	//등록, 수정, 삭제, 한건조회, 목록
	public List<ReplyVO> replySelectList(int noticeId);
	public ReplyVO replySelect(int replyId);
	public int replyInsert(ReplyVO vo);
	public int replyUpdate(ReplyVO vo);
	public int replyDelete(ReplyVO vo);
}
