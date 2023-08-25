package co.yedam.hello.product.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.hello.product.service.ProductService;
import co.yedam.hello.product.service.ProductVO;
import co.yedam.hello.product.serviceImpl.ProductServiceImpl;

public class ProductManager { // 제품관리 메뉴

	private Scanner sc = new Scanner(System.in);
	ProductService dao = new ProductServiceImpl();

	private void mainTitle() {
		System.out.println("");
		System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
		System.out.println("▦            제품관리           ▦");
		System.out.println("▦          1. 전체조회          ▦");
		System.out.println("▦          2. 단건조회          ▦");
		System.out.println("▦          3. 제품등록          ▦");
		System.out.println("▦          4. 제품수정          ▦");
		System.out.println("▦          5. 제품삭제          ▦");
		System.out.println("▦          6. 종 료            ▦");
		System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
		System.out.println("= 작업번호를 선택하세요.. ");
	}

	public void run() {
		boolean b = false;
		do {
			mainTitle();
			int jobCode = sc.nextInt();
			sc.nextLine();
			switch (jobCode) {
			case 1:
				// 제품 전체조회
				System.out.println("----------------------------");
				System.out.println("------- 제 품 전 체 목 록 ------");
				System.out.println("----------------------------");
				productList(); // 전체조회 메소드
				System.out.println("----------------------------");
				break;
			case 2:
				// 제품 단건조회
				System.out.println("----------------------------");
				System.out.println("조회할 제품코드를 입력하세요.");
				String id = sc.nextLine();
				System.out.println("----------------------------");
				productSelect(id); // 단건조회 메소드
				System.out.println("----------------------------");
				break;
			case 3:
				// 제품등록
				System.out.println("--------- 제 품 등 록 --------");
				productInsert();
				System.out.println("----------------------------");
				break;
			case 4:
				// 제품수정
				System.out.println("--------- 제 품 수 정 --------");
				productUpdate();
				System.out.println("----------------------------");
				break;
			case 5:
				// 제품삭제
				System.out.println("--------- 제 품 삭 제 --------");
				productDelete();
				System.out.println("----------------------------");
				break;
			case 6:
				// 종료
				b = true;
				System.out.println("작업이 종료 됩니다.");
				sc.close();
				break;
			}
		} while (!b);
	}

	private void productDelete() {
		ProductVO vo = new ProductVO();
		System.out.println("삭제할 제품의 코드를 입력하세요.");
		vo.setProductId(sc.nextLine());
		
		int n = dao.prodectDelete(vo);
		if (n != 0) {
			System.out.println("제품 삭제를 성공했습니다.");
		} else {
			System.out.println("제품 삭제를 실패했습니다.");
		}
	}

	private void productUpdate() {
		// 먼저 수정항목 선택
		ProductVO vo = new ProductVO();
		System.out.println("수정할 제품의 코드를 입력하세요.");
		vo.setProductId(sc.nextLine());
		subtitle();
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
		case 1:
			// 모든항목
			System.out.print("== 제품명 입력: ");
			vo.setProductName(sc.nextLine());
			System.out.print("== 제품가격 입력: ");
			vo.setProductPrice(sc.nextInt());
			sc.nextLine();
			System.out.print("== 제품모델 입력: ");
			vo.setProductModel(sc.nextLine());
			break;

		case 2:
			// 제품명
			System.out.print("== 제품명 입력: ");
			vo.setProductName(sc.nextLine());
			break;
		case 3:
			// 제품가격
			System.out.print("== 제품가격 입력: ");
			vo.setProductPrice(sc.nextInt());
			sc.nextLine();
			break;
		case 4:
			// 제품모델
			System.out.print("== 제품모델 입력: ");
			vo.setProductModel(sc.nextLine());
			break;

		default:
			System.out.println("[1]~[4]번까지 입력가능합니다.");
			break;
		}

		int n = dao.prodectUpdate(vo);
		if (n != 0) {
			System.out.println("제품정보 변경에 성공했습니다.");
		} else {
			System.out.println("제품정보 변경에 실패했습니다.");
		}
	}

	private void subtitle() {
		System.out.println("====================");
		System.out.println("== 수정할 항목번호 선택 ==");
		System.out.println("====================");
		System.out.println("== [1] 모든항목 =======");
		System.out.println("== [2] 제품명 ========");
		System.out.println("== [3] 제품가격 =======");
		System.out.println("== [4] 제품모델 =======");
		System.out.println("====================");
		System.out.println("수정항목 선택 =>");

	}

	private void productInsert() {
		ProductVO vo = new ProductVO();
		System.out.println("== 제품코드를 입력하세요?");
		vo.setProductId(sc.nextLine());
		System.out.println("== 제품명을 입력하세요?");
		vo.setProductName(sc.nextLine());
		System.out.println("== 제품가격을 입력하세요?");
		vo.setProductPrice(Integer.parseInt(sc.nextLine()));
		System.out.println("== 제품모델을 입력하세요?");
		vo.setProductModel(sc.nextLine());

		int n = dao.prodectInsert(vo);

		if (n != 0) {
			System.out.println("※정상적으로 등록되었습니다!");
		} else {
			System.out.println("※등록에 실패했습니다.");
		}

	}

	private void productSelect(String id) {
		// 제품 단건조회
		ProductVO vo = new ProductVO();
		vo.setProductId(id);
		vo = dao.productSelect(vo);

		if (vo != null) {
			vo.toString();
		} else {
			System.out.println("조회된 제품이 없습니다.");
		}
	}

	private void productList() {
		// 제품 전체조회
		List<ProductVO> products = new ArrayList<ProductVO>();

		products = dao.productSelectList();
		for (ProductVO p : products) {
			p.toString();
		}

	}

}
