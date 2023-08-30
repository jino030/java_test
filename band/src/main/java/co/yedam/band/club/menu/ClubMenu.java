package co.yedam.band.club.menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import co.yedam.band.board.menu.BoardMenu;
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

		// 성공 : 조회내역 보여주기, 실패 : 등록되지 않음 띄워주기
		if (club != null) {
			clubInfo(club, clubMember); // 조회결과 출력
			String result = clubMemberCheck(vo, club); // 회원 상태 반환
			BoardMenu bm = new BoardMenu(vo.getMemberId(), clubName); // 보드메뉴 호출

			boolean b = true;

			// 회원 종류에 따라 옵션 출력 및 실행
			switch (result) {
			case "owner":

				while (b) {
					System.out.println(" ## [옵션] 1.밴드수정 / 2.밴드삭제 / 3.밴드게시판 / 4.메인메뉴 ");
					System.out.print(" ## 원하시는 옵션을 선택하세요 >> ");
					int key = -1;

					try {
						key = scn.nextInt();
					} catch (InputMismatchException e) {
						// 오류가 발생하면 switch의 default로..
					} finally {
						scn.nextLine();
					}

					switch (key) {
					case 1:
						// 밴드수정
						clubUpdate(clubName);
						b = false;
						break;
					case 2:
						// 밴드삭제
						clubDelete(clubName);
						b = false;
						break;
					case 3:
						// 밴드게시판
						bm.run();
						return; // 메인메뉴로.
					case 4:
						// 메인메뉴
						System.out.println(" ## 메인메뉴로 이동합니다.");
						b = false;
						break;
					default:
						System.out.println(" ## 잘못 선택하셨습니다. 1~4까지 숫자만 입력 가능합니다.");
						break;
					}
				}
				break;

			case "member":

				while (b) {
					System.out.println(" ★가입된 밴드입니다.");
					System.out.println(" ## [옵션] 1.밴드탈퇴 / 2.밴드게시판 / 3.메인메뉴 ");
					System.out.print(" ## 원하시는 옵션을 선택하세요 >> ");
					int key = -1;

					try {
						key = scn.nextInt();
					} catch (InputMismatchException e) {
						// 오류가 발생하면 switch의 default로..
					} finally {
						scn.nextLine();
					}

					switch (key) {
					case 1:
						// 밴드탈퇴
						clubMemberDelete(clubName);
						b = false;
						break;
					case 2:
						// 밴드게시판
						bm.run();
						return; // 메인메뉴로.
					case 3:
						// 메인메뉴
						System.out.println(" ## 메인메뉴로 이동합니다.");
						b = false;
						break;
					default:
						System.out.println(" ## 잘못 선택하셨습니다. 1~3까지 숫자만 입력 가능합니다.");
						break;
					}
				}
				break;

			case "nonmember":

				while (b) {
					System.out.println(" ## [옵션] 1.밴드가입 / 2.메인메뉴");
					System.out.print(" ## 원하시는 옵션을 선택하세요 >> ");
					int key = -1;

					try {
						key = scn.nextInt();
					} catch (InputMismatchException e) {
						// 오류가 발생하면 switch의 default로..
					} finally {
						scn.nextLine();
					}

					switch (key) {
					case 1:
						// 밴드가입
						clubMemberInsert(clubName);
						b = false;
						break;
					case 2:
						// 메인메뉴
						System.out.println(" ## 메인메뉴로 이동합니다.");
						b = false;
						break;
					default:
						System.out.println(" ## 잘못 선택하셨습니다. 1~2까지 숫자만 입력 가능합니다.");
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

	private void clubMemberInsert(String clubName) {
		ClubMemberVO clubMember = new ClubMemberVO();
		clubMember.setMemberId(vo.getMemberId());
		clubMember.setClubName(clubName);

		int n = mdao.clubMemberInsert(clubMember);

		if (n != 0) {
			System.out.println(" ## 정상적으로 가입되었습니다!");
		} else {
			System.out.println(" ## 죄송합니다, 오류가 발생했습니다. 다시 시도해주세요.");
		}
	}

	private void clubMemberDelete(String clubName) {
		ClubMemberVO clubMember = new ClubMemberVO();
		clubMember.setMemberId(vo.getMemberId());
		clubMember.setClubName(clubName);

		System.out.println("\n" 
		        + " |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|\r\n" 
				+ " |　주의!　　　　　　　　　　         [-][×] |\r\n"
				+ " |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|\r\n" 
				+ " |　탈퇴하시면 복구는 불가능 합니다                |\r\n"
				+ " |　정말 탈퇴하시겠습니까?                      |\r\n" 
				+ " |　　　＿＿＿＿＿＿　　　＿＿＿＿＿＿　　　　|\r\n"
				+ " | 　　｜    Y    |    ｜    N    ｜　 　　|\r\n" 
				+ " |　　　￣￣￣￣￣￣　　　￣￣￣￣￣￣　　　　|\r\n"
				+ " ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n");
		System.out.print(" ## (Y/N) >> ");
		String yn = scn.nextLine();

		if (yn.equals("Y") || yn.equals("y")) {
			int n = mdao.clubMemberDelete(clubMember);

			if (n != 0) {
				System.out.println(" ## 탈퇴 되었습니다.");
			} else {
				System.out.println(" ## 탈퇴하지 못했습니다.");
			}
		} else if (yn.equals("N") || yn.equals("n")) {
			System.out.println(" ## 취소 되었습니다.");
		} else {
			System.out.println(" ## 잘못 누르셨습니다. 메인화면으로 돌아갑니다.");
		}
	}

	private void clubDelete(String clubName) {

		System.out.println("\n" 
		        + " |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|\r\n" 
				+ " |　주의!　　　　　　　　　　         [-][×] |\r\n"
				+ " |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|\r\n" 
				+ " |　삭제하시면 복구는 불가능 합니다                |\r\n"
				+ " |　정말 삭제하시겠습니까?                      |\r\n" 
				+ " |　　　＿＿＿＿＿＿　　　＿＿＿＿＿＿　　　　|\r\n"
				+ " | 　　｜    Y    |    ｜    N    ｜　 　　|\r\n" 
				+ " |　　　￣￣￣￣￣￣　　　￣￣￣￣￣￣　　　　|\r\n"
				+ " ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n");
		System.out.print(" ## (Y/N) >> ");
		String yn = scn.nextLine();

		if (yn.equals("Y") || yn.equals("y")) {
			int n = dao.clubDelete(clubName);

			if (n != 0) {
				System.out.println(" ## 삭제 되었습니다.");
			} else {
				System.out.println(" ## 삭제하지 못했습니다.");
			}
		} else if (yn.equals("N") || yn.equals("n")) {
			System.out.println(" ## 취소 되었습니다.");
		} else {
			System.out.println(" ## 잘못 누르셨습니다. 메인화면으로 돌아갑니다.");
		}
	}

	private void clubUpdate(String clubName) {
		ClubVO vo = new ClubVO();
		System.out.print(" ## [수정] 밴드소개 문구 >> ");
		vo.setClubComent(scn.nextLine());
		vo.setClubName(clubName);

		int n = dao.clubUpdate(vo);

		if (n != 0) {
			System.out.println(" ## 정상적으로 수정되었습니다!");
		} else {
			System.out.println(" ## 수정하지 못했습니다.");
		}

	}

	private String clubMemberCheck(MemberVO vo, ClubVO club) {
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
		System.out.println(" --------------------------------------------");
		System.out.println("                  밴드 둘러보기                  ");
		System.out.println(" --------------------------------------------");
		if (clubList.size() == 0) {
			System.out.println("              생성된 밴드가 없습니다!!");
		} else {
			for (ClubVO c : clubList) {
				c.clubPrint();
			}
		}
		System.out.println(" --------------------------------------------");
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
		System.out.println(" --------------------------------------------");
		System.out.println("                  밴드 상세조회                  ");
		System.out.println(" --------------------------------------------");
		System.out.println("    ♥ 밴드 이름 : " + club.getClubName());
		System.out.println("    ♥ 밴드 주인 : " + club.getMemberId());
		System.out.println("    ♥ 밴드 소개 : " + club.getClubComent());
		System.out.println("    ♥ 개설일자 : " + club.getClubMakeDate());
		System.out.println("    ♥ 멤버수 : " + clubMember.getMemberCount());
		System.out.println(" --------------------------------------------");
	}

}
