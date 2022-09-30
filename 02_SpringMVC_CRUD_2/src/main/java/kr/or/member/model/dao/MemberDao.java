package kr.or.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public MemberDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int insertMember(Member m) {
		String query = "insert into member_tbl values(member_seq.nextval,?,?,?,?,?)";
		Object[] params = { m.getMemberId(), m.getMemberPw(), m.getMemberName(), m.getPhone(), m.getEmail() };
		int result = jdbcTemplate.update(query, params);
		return result;
	}

	public Member selectOneMember(Member m) {
		String query = "select * from member_tbl where member_id=? and member_pw=?";
		Object[] params = { m.getMemberId(), m.getMemberPw() };
		List list = jdbcTemplate.query(query, params, new MemberRowMapper());
		if (list.isEmpty()) {
			return null;
		} else {
			Member member = (Member) list.get(0);
			return member;
		}
	}

}
