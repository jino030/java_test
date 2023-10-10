package co.yedam.prjdb.book.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.book.map.BookMapper;
import co.yedam.prjdb.book.service.BookService;
import co.yedam.prjdb.book.service.BookVO;
import co.yedam.prjdb.common.DataSource;

public class BookServiceImpl implements BookService {
	
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private BookMapper map = sqlSession.getMapper(BookMapper.class);

	@Override
	public List<BookVO> bookSelectList() {
		return map.bookSelectList();
	}

	@Override
	public BookVO bookSelect(BookVO vo) {
		return map.bookSelect(vo);
	}

	@Override
	public int bookInsert(BookVO vo) {
		return map.bookInsert(vo);
	}

	@Override
	public int bookDelete(BookVO vo) {
		return map.bookDelete(vo);
	}

	@Override
	public int bookUpdate(BookVO vo) {
		return map.bookUpdate(vo);
	}

}
