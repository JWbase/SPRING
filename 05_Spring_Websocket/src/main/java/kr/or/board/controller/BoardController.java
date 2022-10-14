package kr.or.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.FileRename;
import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired
	private FileRename fileRename;
	
	@RequestMapping(value = "/boardList.do")
	public String boardList(int reqPage, Model model) {
		HashMap<String, Object> map = service.selectBoardList(reqPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("pageNavi", map.get("pageNavi"));
		model.addAttribute("reqPage", map.get("reqPage"));
		model.addAttribute("numPerPage", map.get("numPerPage"));
		return "board/boardList";
	}

	@RequestMapping(value = "/boardView.do")
	public String boardView(int boardNo, Model model) {
		Board b = service.selectOneBoard(boardNo);
		model.addAttribute("b", b);
		return "board/boardView";
	}

	@RequestMapping(value = "/boardWriteFrm.do")
	public String boardWriteFrm() {
		return "board/boardWriteFrm";
	}

	@RequestMapping(value = "/boardWrite.do")
	public String boardWrite(Board b, MultipartFile[] boardFile, HttpServletRequest request) {

		// 파일목록 저장할 리스트
		ArrayList<FileVO> fileList = new ArrayList<FileVO>();

		if (!boardFile[0].isEmpty()) {
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");

			for (MultipartFile file : boardFile) {
				String filename = file.getOriginalFilename();
				String filepath = fileRename.fileRename(savePath, filename);

				try {
					FileOutputStream fos = new FileOutputStream(new File(savePath + filepath));
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					byte[] bytes = file.getBytes();
					bos.write(bytes);
					bos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				FileVO fileVO = new FileVO();
				fileVO.setFilename(filename);
				fileVO.setFilepath(filepath);
				fileList.add(fileVO);
			}
		}
		b.setFileList(fileList);
		int result = service.insertBoard(b);
		return "redirect:/boardList.do?reqPage=1";
	}
}
