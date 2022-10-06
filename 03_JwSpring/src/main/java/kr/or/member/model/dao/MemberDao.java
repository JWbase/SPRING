package kr.or.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;

@Repository
public class MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public MemberDao() {
		super();
		System.out.println("DAO 완료");
	}

	public Member loginMember(Member m) {
		String query = "select * from member where member_id = ? , member_pw = ?";
		Object[] params = { m.getMemberId(), m.getMemberPw() };
		List list = jdbcTemplate.query(query, params);
		return null;
	}

}
