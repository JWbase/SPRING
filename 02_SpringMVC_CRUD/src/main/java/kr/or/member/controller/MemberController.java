package kr.or.member.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.sun.swing.internal.plaf.metal.resources.metal;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller
public class MemberController {

	// Autowired : 스프링이 만든 객체 중 선언된 변수와 일치하는 타입을 찾아 값을 대입
	@Autowired
	private MemberService service;

	public MemberController() {
		super();
		System.out.println("Controller 생성완료");
	}

	@RequestMapping(value = "/joinFrm.do")
	public String loginFrm() {
		return "member/joinFrm";
	}

	@RequestMapping(value = "/join.do")
	public String join(Member m) {
		int result = service.insertMember(m);
		if (result > 0) {
			return "member/joinSuccess";
		} else {
			return "member/joinFail";
		}
	}

	@RequestMapping(value = "/login.do")
	public String login(Member m, HttpSession session) {
		Member member = service.selectOneMember(m);
		if (member != null) {
			session.setAttribute("m", member);
		}
		// 메인페이지로 이동 -> return "redirect:주소"
		// -> ViewResolver가 앞뒤에 값을 붙이지 않고 주소 요청
		return "redirect:/";
	}

	@RequestMapping(value = "/searchMember.do")
	public String searchMember(String searchId, Model model) {
		Member m = service.selectOneMember(searchId);
		if (m == null) {
			return "redirect:/";
		} else {
			// model : controller와 화면사이에 1회용 데이터를 주고 받을 객체
			// request.setAttribute(); 효과처럼 작동
			model.addAttribute("m", m);
			return "member/searchMember";
		}
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/searchAllMember.do")
	public String searchAllMember(Model model) {
		ArrayList<Member> list = service.selectAllMember();
		model.addAttribute("list", list);
		return "member/allMember";
	}

	@RequestMapping(value = "/mypage.do")
	public String mypage() {
		return "member/mypage";
	}

	@RequestMapping(value = "/update.do")
	public String update(Member member, @SessionAttribute Member m) {
		int result = service.updateMember(member);
		if (result > 0) {
			m.setMemberPw(member.getMemberPw());
			m.setPhone(member.getPhone());
			m.setEmail(member.getEmail());
			return "redirect:/mypage.do";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/deleteMember.do")
	public String deleteMember(int memberNo) {
		int result = service.deleteMember(memberNo);
		if (result > 0) {
			return "redirect:/logout.do";
		} else {
			return "redirect:/";
		}
	}
}