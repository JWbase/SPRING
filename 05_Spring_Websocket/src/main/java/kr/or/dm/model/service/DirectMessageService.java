package kr.or.dm.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.dm.model.dao.DirectMessageDao;
import kr.or.dm.model.vo.DirectMessage;

@Service
public class DirectMessageService {
	@Autowired
	private DirectMessageDao dao;

	@Transactional //커밋 롤백 해야할시 붙여야할 어노테이션
	public int insertDm(DirectMessage dm) {
		return dao.insertDM(dm);
	}

	public ArrayList<DirectMessage> selectDMList(DirectMessage dm) {
		return dao.selectDMList(dm);
	}

	@Transactional
	public DirectMessage selectOneDm(int dmNo) {
		DirectMessage dm = dao.selectOneDm(dmNo);
		if (dm.getReadCheck() == 0) {
			dao.updateReadCheck(dmNo);
		}
		return dm;
	}

	public int dmCount(String memberId) {
		return dao.dmCount(memberId);
	}
}
