package co.yedam.hello.product.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.hello.common.DataSource;
import co.yedam.hello.product.mapper.ProductMapper;
import co.yedam.hello.product.service.ProductService;
import co.yedam.hello.product.service.ProductVO;

public class ProductServiceImpl implements ProductService {
	// db 연결
	// openSession(true) : autocommit
	private SqlSession sqlSession = DataSource.getInstance().openSession(true); // dao get
	// mapper 연결
	private ProductMapper map = sqlSession.getMapper(ProductMapper.class);

	@Override
	public List<ProductVO> productSelectList() {
		return map.productSelectList();
	}

	@Override
	public ProductVO productSelect(ProductVO vo) {
		return map.productSelect(vo);
	}

	@Override
	public int prodectInsert(ProductVO vo) {
		return map.prodectInsert(vo);
	}

	@Override
	public int prodectDelete(ProductVO vo) {
		return map.prodectDelete(vo);
	}

	@Override
	public int prodectUpdate(ProductVO vo) {
		return map.prodectUpdate(vo);
	}

}
