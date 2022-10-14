package kr.or.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.common.LogAdvice1;
import kr.or.common.LogAdvice2;
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
		System.out.println("selectOneMember 시작");
		Member m = dao.selectOneMember(member);
		System.out.println("selectOneMember 끝");
		return m;
	}

	public ArrayList<Member> selectAllMember() {
		System.out.println("메소드 시작");
		ArrayList<Member> list = dao.selectAllMember();
		System.out.println("메소드 끝");
		return list;
	}
	@Transactional
	public int insertMember(Member m) {
		return dao.insertMember(m);
	}
	
	@Transactional
	public int updateMember(Member m) {
		return dao.updateMember(m);
	}
	
	@Transactional
	public int deleteMember(String memberId) {
		return dao.deleteMember(memberId);
	}

	public ArrayList<Member> searchMemberName(String memberName) {
		return dao.searchMemberName(memberName);
	}

	public ArrayList<Member> searchMember1(String type, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("keyword", keyword);
		ArrayList<Member> list = dao.searchMember1(map);
		return list;
	}

	public ArrayList<Member> searchMember2(Member m) {
		return dao.searchMember2(m);
	}

	public ArrayList<String> searchAllMemberId() {
		return dao.searchAllMemberId();
	}

	public ArrayList<Member> searchMember3(String[] memberId) {
		return dao.searchMember3(memberId);
	}

	public ArrayList<Member> searchMember4() {
		return dao.searchMember4();
	}

	public int changePwMember(Member m) {
		// TODO Auto-generated method stub
		return dao.changePw(m);
	}

	public Member findPwMember(Member m) {
		return dao.findPwMember(m);
	}

}