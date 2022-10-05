package kr.or.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	public MemberController() {
		super();
		System.out.println("Controller 완료");
	}
	
	@RequestMapping(value = "/login.do")
	public String login(Member m, HttpSession session) {
		Member member = service.loginMember(m);
	}

}
