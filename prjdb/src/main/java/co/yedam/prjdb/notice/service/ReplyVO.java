package co.yedam.prjdb.notice.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyId;
	private int noticeId;
	private String replyer;
	private String reply;
	private LocalDate replyDate;
	private LocalDate updateDate;
}
