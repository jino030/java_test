package co.yedam.band.club.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClubVO {
	private int no;
	private String clubName;
	private String memberId;
	private LocalDate clubMakeDate;
	private String clubComent;
	
	public void clubPrint() {
		System.out.println("  " + no + ". 모임명(" + clubName + ") / 개설자(" + memberId + ")");
	}
}
