package co.yedam.band.club.map;

import java.util.List;

import co.yedam.band.club.service.ClubMemberVO;

public interface ClubMemberMapper {
	List<ClubMemberVO> clubMemberSelectList(ClubMemberVO vo);
	ClubMemberVO clubMemberCount(ClubMemberVO vo);
	int clubOwnerInsert(ClubMemberVO vo);
	int clubMemberInsert(ClubMemberVO vo);
	int clubMemberDelete(ClubMemberVO vo);
}
