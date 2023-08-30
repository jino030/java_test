package co.yedam.band.board.menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import co.yedam.band.board.service.BoardService;
import co.yedam.band.board.service.BoardVO;
import co.yedam.band.board.serviceImpl.BoardServiceImpl;

public class BoardMenu {

	String memberId;
	String clubName;
	int boardNo;
	Scanner scn = new Scanner(System.in);
	BoardService dao = new BoardServiceImpl();

	public BoardMenu(String memberId, String clubName) {
		this.memberId = memberId;
		this.clubName = clubName;
	}

	private void title() {
		System.out.println("");
		System.out.println(" --------------------------------------------");
		System.out.println("        [" + clubName + "] 밴드의 게시판 ");
		System.out.println(" --------------------------------------------");
		System.out.println("        1. 게시글 전체 목록 조회");
		System.out.println("        2. 게시글 한건 조회");
		System.out.println("        3. 게시글 작성");
		System.out.println("        4. 메인 메뉴");
		System.out.println(" --------------------------------------------");
		System.out.print(" ## 서비스 번호를 선택 >> ");
	}

	public void run() {
		boolean b = true;

		while (b) {
			title();
			int key = -1;

			try {
				key = scn.nextInt();
			} catch (InputMismatchException e) {
				// 숫자외의 값이 들어오면 switch 구문의 default로!
			} finally {
				scn.nextLine();
			}

			switch (key) {
			case 1: // 게시글 전체 목록 조회
				boardSelectList();
				break;
			case 2: // 게시글 한건 조회
				boardSelect();
				break;
			case 3: // 게시글 작성
				boardInsert();
				break;
			case 4: // 이전 메뉴
				System.out.println(" ## 메인메뉴로 돌아갑니다.");
				b = false;
				break;
			}
		}

	}

	private void boardInsert() {
		BoardVO board = new BoardVO();

		System.out.println(" ## 게시글 작성을 시작합니다!");
		board.setMemberId(memberId); // 로그인된 아이디 셋
		board.setClubName(clubName); // 어느 모임의 게시판인지 셋
		System.out.print(" ## 제목 >> ");
		board.setBoardTitle(scn.nextLine());
		System.out.print(" ## 내용 >> ");
		board.setBoardContent(scn.nextLine());
		
		// 사용자가 enter치고 넘어갔을 경우
		if (board.getBoardTitle().equals("") && board.getBoardContent().equals("")) {
			// 제목과 내용 전부 입력하지 않았을 경우
			System.out.println(" ## 변경사항이 없습니다.");
		} else if (board.getBoardTitle().equals("")) {
			// 제목을 입력하지 않았을 경우			
			int n = dao.boardInsertContent(board);
			if (n != 0) {
				System.out.println(" ## 게시물이 정상적으로 등록되었습니다!");
			} else {
				System.out.println(" ## 게시물 등록에 실패했습니다! 다시 시도 해주세요.");
			}
			
		} else if (board.getBoardContent().equals("")) {
			// 내용을 입력하지 않았을 경우
			int n = dao.boardInsertTitle(board);
			if (n != 0) {
				System.out.println(" ## 게시물이 정상적으로 등록되었습니다!");
			} else {
				System.out.println(" ## 게시물 등록에 실패했습니다! 다시 시도 해주세요.");
			}
		}
	}

	private void boardSelect() {
		BoardVO vo = new BoardVO();

		System.out.print(" ## 조회할 게시물 번호 >> ");
		boardNo = scn.nextInt();
		scn.nextLine();
		vo.setBoardNumber(boardNo);
		vo.setClubName(clubName);

		vo = dao.boardSelect(vo);

		if (vo != null) {
			boardInfo(vo); // 조회결과 출력
			String result = myContentCheck(vo); // 내게시글 여부 반환

			// 내게시글 여부에 따라 옵션 출력 및 실행
			boolean b = true;

			switch (result) {
			// 내가 작성한 글일때
			case "Y":
				while (b) {
					System.out.println(" ## [옵션] 1.게시글 수정 / 2.게시글 삭제 / 3.이전메뉴");
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
						// 게시글 수정
						boardUpdate();
						b = false;
						break;
					case 2:
						// 게시글 삭제
						boardDelete();
						b = false;
						break;
					case 3:
						// 이전메뉴
						System.out.println(" ## 이전메뉴로 이동합니다.");
						b = false;
						break;
					default:
						System.out.println(" ## 잘못 선택하셨습니다. 1~3까지 숫자만 입력 가능합니다.");
						break;
					}
				}
				break;

			// 다른 멤버가 작성한 글일때
			case "N":
				while (b) {
					System.out.println(" ## [옵션] 1.좋아요 / 2.싫어요 / 3.이전메뉴");
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
						// 좋아요
						boardLike();
						b = false;
						break;
					case 2:
						// 싫어요
						boardHate();
						b = false;
						break;
					case 3:
						// 이전메뉴
						System.out.println(" ## 이전메뉴로 이동합니다.");
						b = false;
						break;
					default:
						System.out.println(" ## 잘못 선택하셨습니다. 1~3까지 숫자만 입력 가능합니다.");
						break;
					}
				}
				break;
			}

		} else {
			System.out.println("");
			System.out.println(" --------------------------------------------");
			System.out.println("                 게시글 상세조회                  ");
			System.out.println(" --------------------------------------------");
			System.out.println("      조회된 결과가 없습니다.");
			System.out.println(" --------------------------------------------");
		}

	}

	private void boardLike() {
		System.out.println(" ## 좋아요를 눌렀습니다~!");
		BoardVO vo = new BoardVO();
		vo.setBoardLike(1);
		vo.setBoardNumber(boardNo);
		
		int n = dao.boardLikeHate(vo);
		
		if(n != 0) {
			System.out.println(" ## 좋아요가 반영됐습니다!");
		} else {
			System.out.println(" ## 좋아요를 반영하지 못했어요. 다시 시도해주세요.");
		}
	}
	
	private void boardHate() {
		System.out.println(" ## 싫어요를 눌렀습니다~!");
		BoardVO vo = new BoardVO();
		vo.setBoardHate(1);
		vo.setBoardNumber(boardNo);
		
		int n = dao.boardLikeHate(vo);
		
		if(n != 0) {
			System.out.println(" ## 싫어요가 반영됐습니다!");
		} else {
			System.out.println(" ## 싫어요를 반영하지 못했어요. 다시 시도해주세요.");
		}
	}

	private void boardDelete() {
		BoardVO vo = new BoardVO();
		vo.setBoardNumber(boardNo);
		vo.setMemberId(memberId);

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
			int n = dao.boardDelete(vo);

			if (n != 0) {
				System.out.println(" ## 정상적으로 삭제되었습니다!");
			} else {
				System.out.println(" ## 죄송합니다, 오류가 발생했습니다. 다시 시도 해주세요.");
			}
		} else if (yn.equals("N") || yn.equals("n")) {
			System.out.println(" ## 게시물 삭제를 취소합니다.");
		} else {
			System.out.println(" ## 잘못입력하셨습니다. 다시 시도 해주세요.");
		}
	}

	private void boardUpdate() {
		BoardVO vo = new BoardVO();

		System.out.println(" ## 게시글을 수정합니다!");
		vo.setBoardNumber(boardNo);
		vo.setMemberId(memberId);
		System.out.print(" ## 제목 >> ");
		vo.setBoardTitle(scn.nextLine());
		System.out.print(" ## 내용 >> ");
		vo.setBoardContent(scn.nextLine());

		// 사용자가 enter치고 넘어갔을 경우
		if (vo.getBoardTitle().equals("") && vo.getBoardContent().equals("")) {
			// 제목과 내용 전부 입력하지 않았을 경우
			System.out.println(" ## 변경사항이 없습니다.");
		} else if (vo.getBoardTitle().equals("")) {
			// 제목을 입력하지 않았을 경우 : 제목만 null로 처리
			vo.setBoardTitle(null);
		} else if (vo.getBoardContent().equals("")) {
			// 내용을 입력하지 않았을 경우 : 내용만 null로 처리
			vo.setBoardContent(null);
		}
		
		int n = dao.boardUpdate(vo);

		if (n != 0) {
			System.out.println("정상적으로 수정되었습니다!");
		} else {
			System.out.println("수정 실패, 다시 시도해주세요.");
		}
	}

	private String myContentCheck(BoardVO vo) {
		if (vo.getMemberId().equals(memberId)) {
			return "Y";
		}
		return "N";
	}

	private void boardInfo(BoardVO vo) {
		System.out.println("");
		System.out.println(" --------------------------------------------");
		System.out.println("                 게시글 상세조회                  ");
		System.out.println(" --------------------------------------------");
		System.out.println("    ♥ 작성자 : " + vo.getMemberId());
		System.out.println("    ♥ 제목 : " + vo.getBoardTitle());
		System.out.println("    ♥ 내용 : " + vo.getBoardContent());
		System.out.println("    ♥ 작성일자 : " + vo.getBoardWriteDate());
		System.out.println("    ♥ 수정일자 : " + vo.getBoardUpdateDate());
		System.out.println("    ♥ 좋아요 : " + vo.getBoardLike());
		System.out.println("    ♥ 싫어요 : " + vo.getBoardHate());
		System.out.println(" --------------------------------------------");
	}

	private void boardSelectList() {
		BoardVO vo = new BoardVO();
		vo.setClubName(clubName);
		List<BoardVO> boards = dao.boardSelectList(vo);

		System.out.println("");
		System.out.println(" --------------------------------------------");
		System.out.println("                  게시글 전체 조회                ");
		System.out.println(" --------------------------------------------");
		for (BoardVO b : boards) {
			b.boardPrint();
		}
		System.out.println(" --------------------------------------------");
	}
}
