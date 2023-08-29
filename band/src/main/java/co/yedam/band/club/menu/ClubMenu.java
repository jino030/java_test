package co.yedam.band.club.menu;

import java.util.List;
import java.util.Scanner;

import co.yedam.band.club.service.ClubMemberService;
import co.yedam.band.club.service.ClubMemberVO;
import co.yedam.band.club.service.ClubService;
import co.yedam.band.club.service.ClubVO;
import co.yedam.band.club.serviceImpl.ClubMemberServiceImpl;
import co.yedam.band.club.serviceImpl.ClubServiceImpl;
import co.yedam.band.member.service.MemberVO;

public class ClubMenu {

	Scanner scn = new Scanner(System.in);
	ClubService dao = new ClubServiceImpl();
	ClubMemberService mdao = new ClubMemberServiceImpl();
	MemberVO vo = null;

	public ClubMenu(MemberVO member) {
		vo = member;
	}

	public void clubSelect() {
		ClubVO club = new ClubVO();
		ClubMemberVO clubMember = new ClubMemberVO();
		System.out.print(" ## 조회하고 싶은 밴드명을 입력해 주세요 >> ");
		String clubName = scn.nextLine();
		club.setClubName(clubName);
		club = dao.clubSelect(club); // 밴드 정보조회
		clubMember.setClubName(clubName);
		clubMember = mdao.clubMemberCount(clubMember); // 멤버수 가져오기

		// 성공 : 조회내역 보여주기, 실패 : 실패문구 띄워주기
		if (club != null) {
			clubInfo(club, clubMember);
			String result = clubMemberCheck(vo, club); // 회원 상태 반환

			boolean b = true;

			// 회원 종류에 따라 옵션 출력 및 실행
			switch (result) {
			case "owner":

				while (b) {
					System.out.println(" ## [옵션] 1.밴드수정 / 2.밴드삭제 / 3.메인메뉴");
					System.out.println(" ## 원하시는 옵션을 선택하세요 >> ");
					int key = scn.nextInt();
					scn.nextLine();

					switch (key) {
					case 1:
						// 밴드수정
						System.out.println("밴드수정 함수");
						break;
					case 2:
						// 밴드삭제
						System.out.println("밴드삭제 함수");
						break;
					case 3:
						// 메인메뉴
						System.out.println("메인메뉴로 이동합니다.");
						b = false;
						break;
					}
				}
				break;

			case "member":

				while (b) {
					System.out.println(" ★가입된 밴드입니다.");
					System.out.println(" ## [옵션] 1.밴드탈퇴 / 2.메인메뉴");
					System.out.println(" ## 원하시는 옵션을 선택하세요 >> ");
					int key = scn.nextInt();
					scn.nextLine();

					switch (key) {
					case 1:
						// 밴드탈퇴
						System.out.println("밴드탈퇴 함수");
						break;
					case 2:
						// 메인메뉴
						System.out.println("메인메뉴로 이동합니다.");
						b = false;
						break;
					}
				}
				break;

			case "nonmember":

				while (b) {
					System.out.println(" ## [옵션] 1.밴드가입 / 2.메인메뉴");
					System.out.println(" ## 원하시는 옵션을 선택하세요 >> ");
					int key = scn.nextInt();
					scn.nextLine();

					switch (key) {
					case 1:
						// 밴드가입
						System.out.println("밴드가입 함수");
						break;
					case 2:
						// 메인메뉴
						System.out.println(" ## 메인메뉴로 이동합니다.");
						b = false;
						break;
					}
				}
				break;
			}

		} else {
			System.out.println("");
			System.out.println("--------------------------------------------");
			System.out.println("                 밴드 상세조회                  ");
			System.out.println("--------------------------------------------");
			System.out.println("   ! [" + clubName + "] : 등록되지 않은 밴드 ");
			System.out.println("--------------------------------------------");
		}

	}

	// 상세조회 성공 시 : 모임주인지 여부 확인, 밴드 가입상태 여부 확인
	public String clubMemberCheck(MemberVO vo, ClubVO club) {

		if (vo.getMemberId().equals(club.getMemberId())) {
			// 모임주 이면
			return "owner";
		} else {
			// 가입여부
			ClubMemberVO clubMember = new ClubMemberVO();
			clubMember.setClubName(club.getClubName());
			List<ClubMemberVO> clubMemberList = mdao.clubMemberSelectList(clubMember); // 해당 밴드의 멤버리스트 조회

			// 모임주가 아닐때
			int count = 0;
			for (ClubMemberVO cm : clubMemberList) {
				if (cm.getMemberId().equals(vo.getMemberId())) {
					// 가입이 되어 있으면 count++, 가입이 안되어 있으면 count = 0
					count++;
				}
			}
			
			if (count != 0) {
				return "member";
			} else {
				return "nonmember";
			}
		}
	}

	public void clubSelectList() {
		List<ClubVO> clubList = dao.clubSelectList();

		System.out.println("");
		System.out.println("--------------------------------------------");
		System.out.println("                 밴드 둘러보기                  ");
		System.out.println("--------------------------------------------");
		for (ClubVO c : clubList) {
			c.clubPrint();
		}
		System.out.println("--------------------------------------------");
		System.out.println(" ## 총 " + clubList.size() + "개의 밴드가 만들어져있습니다!");
	}

	public void clubInsert() {
		ClubVO club = new ClubVO();

		System.out.println(" ## 밴드 생성을 시작합니다!");
		club.setMemberId(vo.getMemberId());
		System.out.print(" ## 밴드 이름 >> ");
		club.setClubName(scn.nextLine());
		System.out.print(" ## 밴드 소개 >> ");
		club.setClubComent(scn.nextLine());

		// 모임생성
		int c = dao.clubInsert(club);
		// 모임주 club_member 테이블에 추가
		ClubMemberVO clubMember = new ClubMemberVO();
		clubMember.setClubName(club.getClubName());
		clubMember.setMemberId(club.getMemberId());
		int cm = mdao.clubOwnerInsert(clubMember);

		if (c != 0 && cm != 0) {
			System.out.println(" ## 밴드가 정상적으로 생성되었습니다!");
		} else {
			System.out.println(" ## 밴드 생성 도중 오류가 발생했습니다. 다시 시도해주세요.");
		}
	}

	private void clubInfo(ClubVO club, ClubMemberVO clubMember) {
		System.out.println("");
		System.out.println("--------------------------------------------");
		System.out.println("                 밴드 상세조회                  ");
		System.out.println("--------------------------------------------");
		System.out.println("   ♥ 밴드 이름 : " + club.getClubName());
		System.out.println("   ♥ 밴드 주인 : " + club.getMemberId());
		System.out.println("   ♥ 밴드 소개 : " + club.getClubComent());
		System.out.println("   ♥ 개설일자 : " + club.getClubMakeDate());
		System.out.println("   ♥ 멤버수 : " + clubMember.getMemberCount());
		System.out.println("--------------------------------------------");
	}

}
