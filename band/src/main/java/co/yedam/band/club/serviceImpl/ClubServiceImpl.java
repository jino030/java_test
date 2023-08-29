package co.yedam.band.club.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.band.club.map.ClubMapper;
import co.yedam.band.club.service.ClubService;
import co.yedam.band.club.service.ClubVO;
import co.yedam.band.common.DataSource;

public class ClubServiceImpl implements ClubService {

	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private ClubMapper map = sqlSession.getMapper(ClubMapper.class);
	
	
	@Override
	public List<ClubVO> clubSelectList() {
		return map.clubSelectList();
	}

	@Override
	public ClubVO clubSelect(ClubVO vo) {
		return map.clubSelect(vo);
	}

	@Override
	public int clubInsert(ClubVO vo) {
		return map.clubInsert(vo);
	}

	@Override
	public int clubUpdate(ClubVO vo) {
		return map.clubUpdate(vo);
	}

	@Override
	public int clubDelete(String clubName) {
		return map.clubDelete(clubName);
	}

}
