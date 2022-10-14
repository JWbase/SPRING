package kr.or.dm.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.dm.model.vo.DirectMessage;

@Repository
public class DirectMessageDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insertDM(DirectMessage dm) {
		return sqlSession.insert("directMessage.insertDm", dm);
	}

	public ArrayList<DirectMessage> selectDMList(DirectMessage dm) {
		List list = sqlSession.selectList("directMessage.selectDMList", dm);
		return (ArrayList<DirectMessage>) list;
	}

	public DirectMessage selectOneDm(int dmNo) {
		return sqlSession.selectOne("directMessage.selectOneDm", dmNo);
	}

	public void updateReadCheck(int dmNo) {
		sqlSession.update("directMessage.updateReadCheck", dmNo);

	}

	public int dmCount(String memberId) {
		return sqlSession.selectOne("directMessage.dmCount", memberId);
	}
}
