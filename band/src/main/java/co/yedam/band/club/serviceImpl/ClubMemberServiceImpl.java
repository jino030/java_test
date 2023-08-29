package co.yedam.band.club.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.band.club.map.ClubMemberMapper;
import co.yedam.band.club.service.ClubMemberService;
import co.yedam.band.club.service.ClubMemberVO;
import co.yedam.band.common.DataSource;

public class ClubMemberServiceImpl implements ClubMemberService {

	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private ClubMemberMapper map = sqlSession.getMapper(ClubMemberMapper.class);
	

	@Override
	public List<ClubMemberVO> clubMemberSelectList(ClubMemberVO vo) {
		return map.clubMemberSelectList(vo);
	}

	@Override
	public ClubMemberVO clubMemberCount(ClubMemberVO vo) {
		return map.clubMemberCount(vo);
	}

	@Override
	public int clubOwnerInsert(ClubMemberVO vo) {
		return map.clubOwnerInsert(vo);
	}

	@Override
	public int clubMemberInsert(ClubMemberVO vo) {
		return map.clubMemberInsert(vo);
	}

	@Override
	public int clubMemberDelete(ClubMemberVO vo) {
		return map.clubMemberDelete(vo);
	}

}
