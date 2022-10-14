package kr.or.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;

@Repository
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public Member selectOneMember(Member member) {
		Member m = sqlSession.selectOne("member.selectOneMember", member);
		return m;
	}

	public ArrayList<Member> selectAllMember() {
		List list = sqlSession.selectList("member.selectAllMember");
		return (ArrayList<Member>) list;
	}

	public int insertMember(Member m) {
		int result = sqlSession.insert("member.insertMember", m);
		return result;
	}

	public int updateMember(Member m) {
		int result = sqlSession.update("member.updateMember", m);
		return result;
	}

	public int deleteMember(String memberId) {
		int result = sqlSession.delete("member.deleteMember", memberId);
		return result;
	}

	public ArrayList<Member> searchMemberName(String memberName) {
		List list = sqlSession.selectList("member.searchMemberName", memberName);
		return (ArrayList<Member>) list;
	}

	public ArrayList<Member> searchMember1(HashMap<String, Object> map) {
		List list = sqlSession.selectList("member.searchMember1", map);
		return (ArrayList<Member>) list;
	}

	public ArrayList<Member> searchMember2(Member m) {
		List list = sqlSession.selectList("member.searchMember2", m);
		return (ArrayList<Member>) list;
	}

	public ArrayList<String> searchAllMemberId() {
		List list = sqlSession.selectList("member.searchAllMemberId");
		return (ArrayList<String>) list;
	}

	public ArrayList<Member> searchMember3(String[] memberId) {
		List list = sqlSession.selectList("member.searchMember3", memberId);
		//		HashMap<String, Object> map = new HashMap<String, Object>();
		//		map.put("array", memberId);
		//		List list = sqlSession.selectList("member.searchMember3", map);
		return (ArrayList<Member>) list;
	}

	public ArrayList<Member> searchMember4() {
		List list = sqlSession.selectList("member.searchMember4");
		return (ArrayList<Member>) list;
	}

	public int changePw(Member m) {
		return sqlSession.update("member.changePw", m);
	}

	public Member findPwMember(Member m) {
		return sqlSession.selectOne("member.findPwMember", m);
	}

}
