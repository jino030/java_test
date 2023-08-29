package co.yedam.band;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.yedam.band.common.SHA256;
import co.yedam.band.member.menu.MemberMenu;
import co.yedam.band.member.service.MemberService;
import co.yedam.band.member.service.MemberVO;
import co.yedam.band.member.serviceImple.MemberServiceImple;

public class MenuManager {

	private Scanner scn = new Scanner(System.in);
	private MemberService dao = new MemberServiceImple();
	private SHA256 sha256 = new SHA256(); // 암호화
	private MemberMenu mm = new MemberMenu();

	public void welcomeMessage() {
		System.out.println(" ## Bend Club에 방문해주셔서 감사합니다!");
		System.out.println(" ## 서비스를 이용하시려면 로그인 또는 회원가입을 진행해주세요!");
	}

	public void title() {
		System.out.println("");
		System.out.println("--------------------------------------------");
		System.out.println("                   M E N U                  ");
		System.out.println("--------------------------------------------");
		System.out.println("       1. 로그인");
		System.out.println("       2. 회원가입");
		System.out.println("       3. 프로그램 종료");
		System.out.println("--------------------------------------------");
		System.out.print(" ## 서비스 번호를 선택 >> ");
	}

	public void run() {
		welcomeMessage();
		boolean b = true;

		while (b) {
			title();
			int key = -1;
			try {
				key = scn.nextInt();
			} catch(InputMismatchException e) {
				// 숫자외의 값이 들어오면 switch 구문의 default로!
			} finally {
				scn.nextLine();
			}

			switch (key) {
			case 1: // 로그인
				MemberVO vo = login();
				if (vo != null) {
					mm.run(vo); // 회원만 메인메뉴 접속가능
					b = false;
				}
				break;
			case 2: // 회원가입
				join();
				break;
			case 3:
				System.out.println(" ## 프로그램을 종료합니다!");
				b = false;
				scn.close();
				break;
			default:
				System.out.println(" ## 잘못 선택하셨습니다. 서비스 번호는 숫자만 입력 가능합니다.");
				break;
			}
		}
	}// end of run();

	private MemberVO login() {
		MemberVO vo = new MemberVO();

		System.out.print(" ## 아이디 입력 >> ");
		vo.setMemberId(scn.nextLine());
		System.out.print(" ## 비밀번호 입력 >> ");
		vo.setMemberPassword(scn.nextLine());
		vo.setMemberPassword(sha256.encrypt(vo.getMemberPassword()));

		vo = dao.memberSelect(vo);

		if (vo != null) {
			System.out.println("");
			System.out.println(" ## " + vo.getMemberName() + "님 환영합니다~♬");
			return vo;
		} else {
			System.out.println("");
			System.out.println(" ## 아이디 또는 패스워드가 틀렸습니다.");
			System.out.println(" ## 다시 시도하거나, 회원가입 후 이용해주시기 바랍니다!");
		}

		return null;
	}// end of login()

	private void join() {
		MemberVO vo = new MemberVO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		System.out.print(" ## 아이디 입력 >> ");
		vo.setMemberId(scn.nextLine());
		System.out.print(" ## 비밀번호 입력 >> ");
		vo.setMemberPassword(sha256.encrypt(scn.nextLine())); // 암호화
		System.out.print(" ## 이름 입력 >> ");
		vo.setMemberName(scn.nextLine());
		System.out.print(" ## 생일(ex.19990101) 입력 >> ");
		LocalDate birth = LocalDate.parse(scn.nextLine(), formatter);
		vo.setMemberBirth(birth);
		System.out.print(" ## 이메일 입력 >> ");
		vo.setMemberEmail(scn.nextLine());

		int n = dao.memberInsert(vo);

		if (n != 0) {
			System.out.println("");
			System.out.println(" ## 회원가입 성공! 로그인 후 이용 바랍니다.");
		} else {
			System.out.println("");
			System.out.println(" ## 회원가입 실패! 다시 시도해주세요.");
		}
	}// end of join()
}
