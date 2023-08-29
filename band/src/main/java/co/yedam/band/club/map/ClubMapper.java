package co.yedam.band.club.map;

import java.util.List;

import co.yedam.band.club.service.ClubVO;

public interface ClubMapper {
	List<ClubVO> clubSelectList();
	ClubVO clubSelect(ClubVO vo);
	int clubInsert(ClubVO vo);
	int clubUpdate(ClubVO vo);
	int clubDelete(ClubVO vo);
}
