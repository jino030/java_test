package co.yedam.band.member.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberVO {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberEmail;
	private LocalDate memberBirth;
	private LocalDate enterDate;
	private LocalDate deleteDate;
	private String usetYn;
	private String memberMessage;
}
