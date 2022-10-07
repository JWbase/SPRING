package kr.or.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;

	public HashMap<String, Object> selectBoardList(int reqPage) {

		// 한페이지 당 보여줄 게시물 수
		int numPerPage = 5;
		//reqPage 1 -> 1 ~ 5, 2 -> 6 ~ 10
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;

		// 계산한 start, end 이용해서 게시물 목록 조회
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		ArrayList<Board> list = dao.selectBoardList(map);
		// pageNavi 시작
		// 전체 페이지 수 계산필요 -> 전체 게시물 수
		int totalCount = dao.selectBoardCount();

		//전체페이지 수 계산
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		String pageNavi = "<nav>";
		pageNavi += "<ul>";
		if (pageNo != 1) {
			pageNavi += "<li><a href='/boardList.do?reqPage=" + (pageNo - 1) + "'>이전</a></li>";
		}
		for (int i = 0; i < pageNaviSize; i++) {
			pageNavi += "<li><a href='/boardList.do?reqPage=" + pageNo + "'>" + pageNo + "</a></li>";
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}

		if (pageNo <= totalPage) {
			pageNavi += "<li><a href='/boardList.do?reqPage=" + pageNo + "'>다음</a></li>";
		}
		pageNavi += "</ul>";
		pageNavi += "</nav>";
		
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("list", list);
		map2.put("pageNavi", pageNavi);
		return map2;
	}
}
