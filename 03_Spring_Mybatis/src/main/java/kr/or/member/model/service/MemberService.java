package kr.or.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;

@Service
public class MemberService {

	@Autowired
	MemberDao dao;

	public MemberService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member selectOneMember(Member member) {
		Member m = dao.selectOneMember(member);
		return m;
	}

	public ArrayList<Member> selectAllMember() {
		return dao.selectAllMember();
	}

	public int insertMember(Member m) {
		return dao.insertMember(m);
	}

	public Member selectOneMember(String memberId) {
		return dao.selectOneMember(memberId);
	}

	public int updateMember(Member m) {
		return dao.updateMember(m);
	}

	public int deleteMember(String memberId) {
		return dao.deleteMember(memberId);
	}

	public ArrayList<Member> searchMemberName(String memberName) {
		return dao.searchMemberName(memberName);
	}

}
