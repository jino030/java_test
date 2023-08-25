package co.yedam.hello.product.service;

import java.util.List;

public interface ProductService {

	List<ProductVO> productSelectList(); //R 전체조회
	ProductVO productSelect(ProductVO vo); //R 하나의 제품 조회
	int prodectInsert(ProductVO vo); //C 제품 등록
	int prodectDelete(ProductVO vo); //D 제품 삭제
	int prodectUpdate(ProductVO vo); //U 제품 변경
	
}
