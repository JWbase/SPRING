package kr.or.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardListRowMapper;
import kr.or.board.model.vo.BoardRowMapper;
import kr.or.board.model.vo.FileRowMapper;
import kr.or.board.model.vo.FileVo;

@Repository
public class BoardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public BoardDao() {
		super();
		System.out.println("Board Dao 생성완료");
	}

	public ArrayList<Board> selectAllboard() {
		// 1. PreparedStatement 방식으로 쿼리문 작성
		String query = "select board_no, board_title, board_writer, board_date from board order by 1 desc";
		List list = jdbcTemplate.query(query, new BoardListRowMapper());
		return (ArrayList<Board>) list;
	}

	public int insertBoard(Board b) {
		String query = "insert into board values (board_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = { b.getBoardTitle(), b.getBoardWriter(), b.getBoardContent() };
		int result = jdbcTemplate.update(query, params);
		return result;
	}

	public Board selectOneBoard(int boardNo) {
		String query = "select * from board where board_no = ?";
		Object[] params = { boardNo };
		List list = jdbcTemplate.query(query, params, new BoardRowMapper());
		if (list.isEmpty()) {
			return null;
		} else {
			Board b = (Board) list.get(0);
			return b;
		}
	}

	public int selectBoardNo() {
		String query = "select max(board_no) from board"; // 가장최근번호 조회
		int boardNo = jdbcTemplate.queryForObject(query, int.class); // select시 rowMapper사용하지 않고 기본형객체 사용 할때
		return boardNo;
	}

	public int insertFile(FileVo fileVO) {
		String query = "insert into file_tbl values(file_seq.nextval,?,?,?)";
		Object[] params = { fileVO.getBoardNo(), fileVO.getFilename(), fileVO.getFilepath() };
		int result = jdbcTemplate.update(query, params);
		return result;
	}

	public ArrayList<FileVo> selectFiles(int boardNo) {
		String query = "select * from file_tbl where board_no = ?";
		Object[] params = { boardNo };
		List list = jdbcTemplate.query(query, params, new FileRowMapper());
		return (ArrayList<FileVo>) list;
	}

	public FileVo selectOneFile(int fileNo) {
		String query = "select * from file_tbl where file_no = ?";
		Object[] params = { fileNo };
		List list = jdbcTemplate.query(query, params, new FileRowMapper());
		if (list.isEmpty()) {
			return null;
		} else {
			FileVo f = (FileVo) list.get(0);
			return f;
		}
	}

	public int updateBoard(Board b) {
		String query = "update board set board_title= ?, board_content=? where board_no=?";
		Object[] params = { b.getBoardTitle(), b.getBoardContent(), b.getBoardNo() };
		int result = jdbcTemplate.update(query, params);
		return result;
	}

	public int deleteFile(int fileNo) {
		String query = "delete from file_tbl where file_no=?";
		Object[] parmas = { fileNo };
		int result = jdbcTemplate.update(query, parmas);
		return result;
	}

	public int deleteBoard(int boardNo) {
		String query = "delete from board where board_no = ?";
		Object[] parmas = {boardNo};
		int result = jdbcTemplate.update(query, parmas);
		return result;
	}

}
