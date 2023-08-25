package co.yedam.hello.product.mapper;

import java.util.List;

import co.yedam.hello.product.service.ProductVO;

// 마이바티스에서 사용할거
public interface ProductMapper {
	List<ProductVO> productSelectList(); //R 전체조회
	ProductVO productSelect(ProductVO vo); //R 하나의 제품 조회
	int prodectInsert(ProductVO vo); //C 제품 등록
	int prodectDelete(ProductVO vo); //D 제품 삭제
	int prodectUpdate(ProductVO vo); //U 제품 변경
}
