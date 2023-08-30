package co.yedam.band.board.map;

import java.util.List;

import co.yedam.band.board.service.BoardVO;

public interface BoardMapper {
	List<BoardVO> boardSelectList(BoardVO vo); // 해당 모임의 게시글만 조회
	BoardVO boardSelect(BoardVO vo);
	int boardInsert(BoardVO vo);
	int boardUpdate(BoardVO vo);
	int boardDelete(BoardVO vo);
	
	// 좋아요, 싫어요
	int boardLikeHate(BoardVO vo);
}
