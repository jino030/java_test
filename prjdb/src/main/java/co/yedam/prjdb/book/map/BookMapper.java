package co.yedam.prjdb.book.map;

import java.util.List;
import java.util.Map;

import co.yedam.prjdb.book.service.BookVO;

public interface BookMapper {
	List<BookVO> bookSelectList();
	BookVO bookSelect(BookVO vo);
	int bookInsert(BookVO vo);
	int bookDelete(BookVO vo);
	int bookUpdate(BookVO vo);
	int bookDeletes(Map<String, BookVO> map);
}
