package co.yedam.prj.member.service;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberTel;
	private LocalDate memberBirth;
	private LocalDate enterDate;
	private LocalDate deleteDate;
	private String useYn;
	private String memberMessage;
	private String memberAuthor;
}
