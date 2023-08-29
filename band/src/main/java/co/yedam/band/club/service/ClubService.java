package co.yedam.band.club.service;

import java.util.List;

public interface ClubService {
	List<ClubVO> clubSelectList();
	ClubVO clubSelect(ClubVO vo);
	int clubInsert(ClubVO vo);
	int clubUpdate(ClubVO vo);
	int clubDelete(String clubName);
}
