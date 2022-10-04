package kr.or.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public MemberDao() {
		super();
		System.out.println("Dao 생성완료");
	}

	public int insertMember(Member m) {
		// 1. PreparedStatement방식으로 쿼리문 작성
		String query = "insert into member_tbl values(member_seq.nextval,?,?,?,?,?)";
		// 2. 위치홀더가 1개라도 있는 경우(없으면 생략) -> 위치홀더 순서대로 작성
		Object[] params = { m.getMemberId(), m.getMemberPw(), m.getMemberName(), m.getPhone(), m.getEmail() };
		// 3. 쿼리문, 위치홀더에 들어갈 값을 이용해서 쿼리문 실행
		// insert/update/delete인경우 update()메소드 사용
		int result = jdbcTemplate.update(query, params);
		return result;
	}

	public Member selectOneMember(Member m) {
		String query = "select * from member_tbl where member_id=? and member_pw=?";
		Object[] params = { m.getMemberId(), m.getMemberPw() };
		// 조회결과가 몇줄인지 모르기때문에 list 타입으로 데이터를 반환
		List list = jdbcTemplate.query(query, params, new MemberRowMapper());
		if (list.isEmpty()) {
			return null;
		} else {
			Member member = (Member) list.get(0);
			return member;
		}
	}

	public Member selectOneMember(String searchId) {
		String query = "select * from member_tbl where member_id=?";
		Object[] params = { searchId };
		List list = jdbcTemplate.query(query, params, new MemberRowMapper());
		if (list.isEmpty()) {
			return null;
		} else {
			Member m = (Member) list.get(0);
			return m;
		}
	}

	public ArrayList<Member> selectAllMember() {
		String query = "select * from member_tbl";
		List<Member> list = jdbcTemplate.query(query, new MemberRowMapper());
		return (ArrayList<Member>) list;
	}

	public int updateMember(Member m) {
		String query = "update member_tbl set member_pw=?, phone=?, email=? where member_id = ?";
		Object[] params = { m.getMemberPw(), m.getPhone(), m.getEmail(), m.getMemberId() };
		int result = jdbcTemplate.update(query, params);
		return result;
	}

	public int deleteMember(int memberNo) {
		String query = "delete from member_tbl where member_no=?";
		Object[] params = {memberNo};
		int result = jdbcTemplate.update(query, params);
		return result;
	}

}
