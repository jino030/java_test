package co.yedam.band.member.menu;

import java.util.Scanner;

import co.yedam.band.club.menu.ClubMenu;
import co.yedam.band.member.service.MemberService;
import co.yedam.band.member.service.MemberVO;
import co.yedam.band.member.serviceImple.MemberServiceImple;

public class MemberMenu {

	Scanner scn = new Scanner(System.in);
	MemberService dao = new MemberServiceImple();

	private void title() {
		System.out.println("");
		System.out.println("--------------------------------------------");
		System.out.println("                   M E N U                  ");
		System.out.println("--------------------------------------------");
		System.out.println("       1. 마이페이지");
		System.out.println("       2. 밴드 만들기");
		System.out.println("       3. 밴드 둘러보기");
		System.out.println("       4. 밴드 상세보기");
		System.out.println("       5. 종료");
		System.out.println("--------------------------------------------");
		System.out.print(" ## 서비스 번호를 선택 >> ");
	}

	public void run(MemberVO vo) {
		ClubMenu cm = new ClubMenu(vo);
		boolean b = true;

		while (b) {
			title();
			int key = scn.nextInt();
			scn.nextLine();

			switch (key) {
			case 1: //1. 마이페이지
				b = mypageMenu(vo);
				break;
			case 2: //2. 밴드 만들기
				cm.clubInsert();
				break;
			case 3: //3. 밴드 둘러보기
				cm.clubSelectList();
				break;
			case 4: //4. 밴드 상세보기
				cm.clubSelect();
				break;
			case 5: //5. 종료
				System.out.println(" ## 프로그램을 종료합니다.");
				b = false;
				scn.close();
				break;
			}
		}
	}

	private boolean memberDelete(MemberVO vo) {
		System.out.print(" ## 정말로 탈퇴하시겠습니까? (Y/N) >> ");
		String yn = scn.nextLine();

		if (yn.equals("Y") || yn.equals("y")) {
			int n = dao.memberDelete(vo);

			if (n != 0) {
				System.out.println(" ## 그동안 이용해 주셔서 감사합니다. 행복하세요!");
				return false;
			} else {
				System.out.println(" ## 죄송합니다, 오류가 발생했습니다. 다시 시도 해주세요.");
			}
		} else if(yn.equals("N") || yn.equals("n")) {
			System.out.println(" ## 떠나시지 않으신다니 정말 다행입니다! 가지마세요~♥");
		} else {
			System.out.println(" ## 잘못입력하셨습니다. 다시 시도 해주세요.");
		}
		
		return true;
	}

	private MemberVO memberUpdate(MemberVO vo) {
		MemberVO member = new MemberVO();
		member.setMemberId(vo.getMemberId());
		System.out.print(" ## 이메일 입력 >> ");
		member.setMemberEmail(scn.nextLine());
		System.out.print(" ## 상태 메세지 입력 >> ");
		member.setMemberMessage(scn.nextLine());

		// 사용자가 enter치고 넘어갔을 경우
		if (member.getMemberEmail().equals("") && member.getMemberMessage().equals("")) {
			// 이메일과 상태메시지 전부 입력하지 않았을 경우 : 원래의 정보 넘겨준다.
			System.out.println(" ## 변경사항이 없습니다.");
			return vo;
		} else if (member.getMemberEmail().equals("")) {
			// 이메일을 입력하지 않았을 경우 : 이메일만 null로 처리
			member.setMemberEmail(null);
		} else if (member.getMemberMessage().equals("")) {
			// 상태메시지를 입력하지 않았을 경우 : 상태메세지만 null로 처리
			member.setMemberMessage(null);
		}

		int n = dao.memberUpdate(member);

		if (n != 0) {
			System.out.println("");
			System.out.println(" ## 정상적으로 수정되었습니다!!");
			return dao.memberSelect(member);
		} else {
			System.out.println("");
			System.out.println(" ## 수정 실패! 오류가 발생했습니다.");
		}
		return member;
	}

	private boolean mypageMenu(MemberVO vo) {
		MemberVO member = vo;
		boolean b = true;

		while (b) {
			mypageTitle(member);
			int key = scn.nextInt();
			scn.nextLine();

			switch (key) {
			case 1:
				member = memberUpdate(member);
				break;
			case 2:
				b = memberDelete(vo);
				return b;
			case 3:
				System.out.println(" ## 메인메뉴로 돌아갑니다~!");
				b = false;
				break;
			}
		}
		return true;
	}

	private void mypageTitle(MemberVO vo) {
		System.out.println("");
		System.out.println("--------------------------------------------");
		System.out.println("                   My Page                  ");
		System.out.println("--------------------------------------------");
		System.out.println("       ♥ 이름 : " + vo.getMemberName());
		System.out.println("       ♥ 아이디 : " + vo.getMemberId());
		System.out.println("       ♥ 이메일 : " + vo.getMemberEmail());
		System.out.println("       ♥ 생일 : " + vo.getMemberBirth());
		System.out.println("       ♥ 상태 메세지 : " + (vo.getMemberMessage() == null ? "-" : vo.getMemberMessage()));
		System.out.println("       ♥ 가입일자 : " + vo.getEnterDate());
		System.out.println("--------------------------------------------");
		System.out.println("       1. 수정하기 / 2. 회원탈퇴 / 3. 메인메뉴");
		System.out.println("--------------------------------------------");
		System.out.print(" ## 서비스 번호를 선택 >> ");
	}

}
