package co.yedam.band;

import java.util.Scanner;

import co.yedam.band.common.SHA256;
import co.yedam.band.member.menu.MemberMenu;
import co.yedam.band.member.service.MemberService;
import co.yedam.band.member.service.MemberVO;
import co.yedam.band.member.serviceImple.MemberServiceImple;

public class MainView {

	Scanner scn = new Scanner(System.in);
	MemberMenu mm = new MemberMenu();
	
	public void welcomeMessage() {
		System.out.println("## Bend Club에 방문해주셔서 감사합니다!");
		System.out.println("## 서비스를 이용하시려면 로그인 또는 회원가입을 진행해주세요!");
	}
	public void mainMenu() {
		System.out.println("--------------------------------------------");
		System.out.println("                   M E N U                  ");
		System.out.println("--------------------------------------------");
		System.out.println("       1. 로그인");
		System.out.println("       2. 회원가입");
		System.out.println("       3. 프로그램 종료");
		System.out.println("--------------------------------------------");
		System.out.print("## 서비스 번호를 선택 >> ");
	}

	public void run() {
		welcomeMessage();
		boolean b = true;
		
		while(b) {
			mainMenu();
			int key = scn.nextInt(); scn.nextLine();
			
			switch(key) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				System.out.println("## 프로그램을 종료합니다!");
				b = false;
				scn.close();
				break;
			default:
				System.out.println("## 잘못 선택하셨습니다.");
				break;
			}
		}
	}// end of run();
	
	private boolean login() {
		MemberService dao =  new MemberServiceImple();
		SHA256 sha256 = new SHA256(); // 암호화
		MemberVO vo = new MemberVO();
		
		System.out.print("## 아이디 입력 >>");
		vo.setMemberId(scn.nextLine());
		System.out.print("## 비밀번호 입력 >>");
		vo.setMemberPassword(sha256.encrypt(scn.nextLine()));

		vo = dao.memberSelect(vo);
		
		if(vo != null) {
			System.out.println(vo.getMemberName() + "님 환영합니다!!");
		}
		
		
		
		return false;
	}
	
	
}
