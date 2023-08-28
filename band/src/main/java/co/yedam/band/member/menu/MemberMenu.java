package co.yedam.band.member.menu;

import java.util.Scanner;

import co.yedam.band.member.service.MemberService;
import co.yedam.band.member.serviceImple.MemberServiceImple;

public class MemberMenu {

	Scanner scn = new Scanner(System.in);
	MemberService dao = new MemberServiceImple();
	
	public void run() {
		System.out.println("이곳은 회원만 들어올 수 있는 뷰 입니다.");
	}
}
