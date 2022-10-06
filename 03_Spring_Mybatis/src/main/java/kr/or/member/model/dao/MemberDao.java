package kr.or.member.model.dao;

import java.util.ArrayList;
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

	public Member selectOneMember(String memberId) {
		Member m = sqlSession.selectOne("member.selectOneMemberId", memberId);
		return m;
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
		List list = sqlSession.selectList("member.selectMemberName");
		return (ArrayList<Member>) list;
	}

}
