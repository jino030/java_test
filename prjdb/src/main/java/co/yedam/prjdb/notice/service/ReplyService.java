package co.yedam.prjdb.notice.service;

import java.util.List;

public interface ReplyService {
	public List<ReplyVO> replySelectList(int noticeId);
	public ReplyVO replySelect(int replyId);
	public int replyInsert(ReplyVO vo);
	public int replyUpdate(ReplyVO vo);
	public int replyDelete(ReplyVO vo);
}
