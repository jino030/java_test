package co.yedam.band.member.map;

import co.yedam.band.member.service.MemberVO;

public interface MemberMapper {
	MemberVO memberSelect(MemberVO vo);
	int memberInsert(MemberVO vo);
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
}
