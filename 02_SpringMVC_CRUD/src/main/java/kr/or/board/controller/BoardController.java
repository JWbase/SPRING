package kr.or.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardFileData;
import kr.or.board.model.vo.FileVo;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;

	public BoardController() {
		super();
		System.out.println("Board Controller 생성완료");
	}

	@RequestMapping(value = "/boardList.do")
	public String boardList(Model model) {
		ArrayList<Board> list = service.selectAllboard();
		model.addAttribute("list", list);
		return "board/boardList";
	}

	@RequestMapping(value = "/boardWriteFrm.do")
	public String boardWriteFrm() {
		return "board/boardWriteFrm";
	}

	@RequestMapping(value = "/boardWrite.do")
	public String boardWrite(Board b) {
		int result = service.insertBoard(b);
		if (result > 0) {
			return "redirect:/boardList.do";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/boardView.do")
	public String selectOneBoard(int boardNo, Model model) {
		BoardFileData bfd = service.selectOneBoard(boardNo);
		if (bfd == null) {
			return "redirect:/boardList.do";
		} else {
			model.addAttribute("b", bfd.getB());
			return "board/boardView";
		}
	}

	@RequestMapping(value = "/boardUpdateFrm.do")
	public String boardUpdateFrm(int boardNo, Model model) {
		Board b = service.boardUpdateFrm(boardNo);
		model.addAttribute("b", b);
		return "board/updateBoardFrm.jsp";
	}

	@RequestMapping(value = "/boardWriteFrm2.do")
	public String boardWriteFrm2() {
		return "board/boardWriteFrm2";
	}

	@RequestMapping(value = "/boardWrite2.do")
	public String boardWrite2(Board b, MultipartFile[] boardFile, HttpServletRequest request) {

		// 파일목록을 저장할 리스트 생성
		ArrayList<FileVo> list = new ArrayList<FileVo>();
		// MultipartFile[]은 jsp에서 첨부한 파일 갯수만큼 길이가 생성
		// 단, 첨부파일은 한개도 첨부하지 않더라도 배열의 길이는 기본적으로 1

		// 첨부파일 없는 경우 배열 첫번째 인덱스의 value 비어있음
		if (boardFile[0].isEmpty()) {
			// 첨부파일이 없는 경우 수행할 로직 없음
		} else {
			// 첨부파일이 있는 경우 파일업로드 수행
			// 1. 파일업로드 경로 설정(getRealPath()까지가 webapp폴더)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");

			// 2. 반복문을 이용한 파일업로드
			for (MultipartFile file : boardFile) {
				// 파일명이 기존 업로드한 파일명과 중복되는 경우 기존파일을 삭제하고 새파일 덮어쓰기
				// 파일명 중복처리 로직
				// 사용자가 업로드한 파일 이름 추출
				String filename = file.getOriginalFilename();

				// ex) filename = test.txt라는 값을 추출한다고 가정
				String onlyFilename = filename.substring(0, filename.lastIndexOf(".")); // 파일이름 0번부터 마지막에오는 .앞까지
				String extention = filename.substring(filename.lastIndexOf(".")); // .부터 끝까지 가져옴

				// 실제 업로드할 파일명
				String filepath = null;

				// 파일명이 중복되는 경우 뒤에 붙일 숫자
				int count = 0;
				while (true) {
					if (count == 0) {
						// 파일이름 체크 -> 첫번째 인 경우
						filepath = onlyFilename + extention; // 원래 파일명 그대로(ex : text.txt)
					} else {
						filepath = onlyFilename + "_" + count + extention; // 파일명_숫자.확장자(ex : text_1.txt)
					}
					File checkFile = new File(savePath + filepath);
					if (!checkFile.exists()) {
						// 파일명이 존재하지않을때 (중복이 아닐때)
						break;
					}
					count++;
				}
				// 파일명 중복체크 끝난 시점 - > 파일업로드 진행

				// 중복처리가 끝난 파일명으로 파일업로드 진행
				try {
					FileOutputStream fos = new FileOutputStream(new File(savePath + filepath));

					// 속도 개선을 위한 보조스트림 사용
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
				// 파일업로드 끝(파일 1개 업로드)
				FileVo fileVO = new FileVo();
				fileVO.setFilename(filename);
				fileVO.setFilepath(filepath); // 실제 업로드 파일
				list.add(fileVO);
			}
		}
		// 데이터베이스에 insert -> 비즈니스로직
		int result = service.insertBoard2(b, list);
		// 성공인 경우 result == list.size() + 1
		return "redirect:/boardList.do";
	}
}