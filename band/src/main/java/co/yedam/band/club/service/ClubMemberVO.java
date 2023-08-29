package co.yedam.band.club.service;

import lombok.Data;

@Data
public class ClubMemberVO {
	private int memberCount;
	private String clubName;
	private String memberId;
	private String clubOwnerYn;
}
