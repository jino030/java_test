package co.yedam.prjdb.book.service;

import java.util.List;

public interface BookService {
	List<BookVO> bookSelectList();
	BookVO bookSelect(BookVO vo);
	int bookInsert(BookVO vo);
	int bookDelete(BookVO vo);
	int bookUpdate(BookVO vo);
}
