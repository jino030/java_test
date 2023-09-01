package co.yedam.project.member.service;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	String memberId;
	String memberPassword;
	String memberName;
	String memberEmail;
	LocalDate memberBirth;
	LocalDate enterDate;
	LocalDate deleteDate;
	String useYn;
	String memberMessage;
}
