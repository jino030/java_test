package co.yedam.band.club.service;

import java.util.List;

public interface ClubMemberService {
	List<ClubMemberVO> clubMemberSelectList(ClubMemberVO vo);
	ClubMemberVO clubMemberCount(ClubMemberVO vo);
	int clubOwnerInsert(ClubMemberVO vo);
	int clubMemberInsert(ClubMemberVO vo);
	int clubMemberDelete(ClubMemberVO vo);
}
