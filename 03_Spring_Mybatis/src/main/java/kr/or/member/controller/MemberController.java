package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;

	public MemberController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/login.do")
	public String login(Member member, HttpSession session) {
		Member m = service.selectOneMember(member);
		if (m != null) {
			session.setAttribute("m", m);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/selectAllMember.do")
	public String selectAllMember(Model model) {
		ArrayList<Member> list = service.selectAllMember();
		model.addAttribute("list", list);
		return "member/memberList";
	}

	@RequestMapping(value = "/joinFrm.do")
	public String joinFrm() {
		return "member/joinFrm";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/join.do")
	public String join(Member m) {
		int result = service.insertMember(m);
		if (result > 0) {
			return "redirect:/";
		} else {
			return "member/joinFrm";
		}
	}

	@RequestMapping(value = "/searchMemberId.do")
	public String searchMemberId(Member member, Model model) {
		Member m = service.selectOneMember(member);
		if (m != null) {
			model.addAttribute("m", m);
			return "member/searchMember";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/mypage.do")
	public String mypage() {
		return "member/mypage";
	}

	@RequestMapping(value = "/update.do")
	public String update(Member m, HttpSession session) {
		int result = service.updateMember(m);
		if (result > 0) {
			Member member = (Member) session.getAttribute("m");
			member.setMemberPw(m.getMemberPw());
			member.setPhone(m.getPhone());
			return "redirect:/mypage.do";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/deleteMember.do")
	public String deleteMember(String memberId, HttpSession session) {
		int result = service.deleteMember(memberId);
		if (result > 0) {
			session.invalidate();
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/searchMemberName.do")
	public String searchMemberName(String memberName, Model model) {
		ArrayList<Member> list = service.searchMemberName(memberName);
		model.addAttribute("list", list);
		return "member/memberList";
	}

	@RequestMapping(value = "/searchMember1.do")
	public String searchMember(String type, String keyword, Model model) {
		ArrayList<Member> list = service.searchMember1(type, keyword);
		model.addAttribute("list", list);
		return "member/memberList";
	}

	@RequestMapping(value = "/searchMember2.do")
	public String searchMember2(Member m, Model model) {
		ArrayList<Member> list = service.searchMember2(m);
		model.addAttribute("list", list);
		return "member/memberList";
	}

	@RequestMapping(value = "/idList.do")
	public String idList(Model model) {
		ArrayList<String> list = service.searchAllMemberId();
		model.addAttribute("list", list);
		return "member/idList";
	}

	@RequestMapping(value = "/searchMember3.do")
	public String searchMember3(String[] memberId, Model model) {
		ArrayList<Member> list = service.searchMember3(memberId);
		model.addAttribute("list", list);
		return "member/memberList";
	}
	
	@RequestMapping(value = "/searchMember4.do")
	public String searchMember4(Model model) {
		ArrayList<Member> list = service.searchMember4();
		model.addAttribute("list", list);
		return "member/memberList";
	}
}
