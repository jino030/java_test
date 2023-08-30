package co.yedam.band.board.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.band.board.map.BoardMapper;
import co.yedam.band.board.service.BoardService;
import co.yedam.band.board.service.BoardVO;
import co.yedam.band.common.DataSource;

public class BoardServiceImpl implements BoardService {

	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private BoardMapper map = sqlSession.getMapper(BoardMapper.class);
	
	@Override
	public List<BoardVO> boardSelectList(BoardVO vo) {
		return map.boardSelectList(vo);
	}

	@Override
	public BoardVO boardSelect(BoardVO vo) {
		return map.boardSelect(vo);
	}

	@Override
	public int boardInsert(BoardVO vo) {
		return map.boardInsert(vo);
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		return map.boardUpdate(vo);
	}

	@Override
	public int boardDelete(BoardVO vo) {
		return map.boardDelete(vo);
	}

	@Override
	public int boardLikeHate(BoardVO vo) {
		return map.boardLikeHate(vo);
	}
}
