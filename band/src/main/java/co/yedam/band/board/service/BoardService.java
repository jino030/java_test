package co.yedam.band.board.service;

import java.util.List;

public interface BoardService {
	List<BoardVO> boardSelectList(BoardVO vo); // 해당 모임의 게시글만 조회
	BoardVO boardSelect(BoardVO vo);
	int boardInsert(BoardVO vo);
	int boardInsertTitle(BoardVO vo);
	int boardInsertContent(BoardVO vo);
	int boardUpdate(BoardVO vo);
	int boardDelete(BoardVO vo);
	
	// 좋아요, 싫어요
	int boardLikeHate(BoardVO vo);
}
