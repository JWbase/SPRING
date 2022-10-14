package kr.or.dm.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.or.dm.model.service.DirectMessageService;
import kr.or.dm.model.vo.DirectMessage;
import lombok.val;

@Controller
public class DirectMessageController {
	@Autowired
	private DirectMessageService service;

	@RequestMapping(value = "/dmMain.do")
	public String dmMain() {
		return "dm/dmMain";
	}

	@ResponseBody
	@RequestMapping(value = "/insertDm.do")
	public String sendDm(DirectMessage dm) {
		int result = service.insertDm(dm);
		return String.valueOf(result);
	}

	@ResponseBody
	@RequestMapping(value = "/myDmList.do", produces = "application/json;charset=utf-8")
	public String myDmList(DirectMessage dm) {
		ArrayList<DirectMessage> list = service.selectDMList(dm);
		return new Gson().toJson(list);
	}

	@ResponseBody
	@RequestMapping(value = "/dmDetail.do", produces = "application/json;charset=utf-8")
	public String dmDetail(int dmNo) {
		DirectMessage dm = service.selectOneDm(dmNo);
		return new Gson().toJson(dm);
	}

}
