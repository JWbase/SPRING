package kr.or.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVO;

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

		// 페이지 네비 길이
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		// int pageNo = 1;
		// if(reqPage>3) {
		//	pageNo = reqPage-2;
		//	}

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
		map2.put("reqPgage", reqPage);
		map2.put("numPerPage", numPerPage);
		return map2;
	}

	public Board selectOneBoard(int boardNo) {
		// board테이블조회
		Board b = dao.selectOneBoard(boardNo);
		// file_tbl 조회
		//		ArrayList<FileVO> list = dao.selectFileList(boardNo);
		//		b.setFileList(list);
		return b;
	}

	public int insertBoard(Board b) {
		int result = dao.insertBoard(b);
		if (result > 0) {
			//int boardNo = dao.selectBoardNo();
			for (FileVO fv : b.getFileList()) {
				//fv.setBoardNo(boardNo);
				fv.setBoardNo(b.getBoardNo());
				result += dao.insertFile(fv);
			}
		}
		return result;
	}
}
