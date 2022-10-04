package kr.or.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileVo {

	private int fileNo;
	private int boardNo;
	private String filename;
	private String filepath;
}
