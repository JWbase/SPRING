package kr.or.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao dao;

	public BoardService() {
		super();
		System.out.println("Board Service 생성완료");
	}

	public ArrayList<Board> selectAllboard() {
		ArrayList<Board> list = dao.selectAllboard();
		return list;

	}

	public int insertBoard(Board b) {
		int result = dao.insertBoard(b);
		return result;
	}

	public Board selectOneBoard(int boardNo) {
		Board b = dao.selectOneBoard(boardNo);
		ArrayList<FileVo> fileList = dao.selectFiles(boardNo);
		b.setFileList(fileList);
		return b;
	}

	public int insertBoard2(Board b, ArrayList<FileVo> list) {
		int result = dao.insertBoard(b);

		// insert가 성공한 경우 파일을 insert
		// 이때 파일이 없으면 insert할 필요가 없음
		if (result > 0) {
			int boardNo = 0;
			if (!list.isEmpty()) {

				// 가장 최근에 들어간 boardNo를 조회
				boardNo = dao.selectBoardNo();
				for (FileVo fileVO : list) {

					//board테이블에 방금 insert한 board_no를 참조하기 위해 setBoard사용
					fileVO.setBoardNo(boardNo);
					result += dao.insertFile(fileVO);
				}
			}
		}
		return result;
	}

	public FileVo getBoard(int fileNo) {
		FileVo f = dao.selectOneFile(fileNo);
		return f;
	}

	public int boardUpdate(Board b, int[] fileNolist) {
		// 1. board테이블 수정(제목, 내용)
		int result = dao.updateBoard(b);
		if (result > 0) {
			// 2. 새 첨부파일이 있으면 insert
			for (FileVo fv : b.getFileList()) {
				fv.setBoardNo(b.getBoardNo());
				result += dao.insertFile(fv);
			}
			// 3. 삭제한 파일이 있으면 delete
			if (fileNolist != null) {
				for (int fileNo : fileNolist) {
					result += dao.deleteFile(fileNo);
				}
			}
		}

		return result;
	}

	public ArrayList<FileVo> boardDelete(int boardNo) {
		ArrayList<FileVo> fileList = dao.selectFiles(boardNo);
		//board테이블에서 삭제
		int result = dao.deleteBoard(boardNo);
		if (result > 0) {
			return fileList;
		} else {
			return null;
		}
	}
}