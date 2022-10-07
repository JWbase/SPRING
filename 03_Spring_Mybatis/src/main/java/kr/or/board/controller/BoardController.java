package kr.or.board.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;

	@RequestMapping(value = "/boardList.do")
	public String boardList(int reqPage, Model model) {
		HashMap<String, Object> map = service.selectBoardList(reqPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("pageNavi", map.get("pageNavi"));
		return "board/boardList";
	}
}
