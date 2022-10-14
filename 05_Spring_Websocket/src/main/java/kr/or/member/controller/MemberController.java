package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

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

	@ResponseBody // 페이지 이동이아닌 데이터자체로 리턴하고 싶을 때(비동기요청)
	@RequestMapping(value = "/idcheck.do")
	public String idCheck(Member m) {
		Member member = service.selectOneMember(m);
		if (member == null) {
			// 사용가능한 아이디
			return "0";
		} else {
			// 사용 불가능한 아이디
			return "1";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/ajaxAllMember.do", produces = "application/json;charset=utf-8")
	public String ajaxAllMember() {
		ArrayList<Member> list = service.selectAllMember();
		Gson gson = new Gson();
		String result = gson.toJson(list);
		System.out.println(result);
		return result;
	}

	@RequestMapping(value = "/pwChangeFrm.do")
	public String pwChangeFrm() {
		return "member/currentPwCheckFrm";
	}

	@RequestMapping(value = "/currentPwCheck.do")
	public String currentPwCheck(Member m) {
		// 데이터베이스에서 pw를 조회 한 후 입력한 비밀번호와 조회한 비밀번호가 같은지 체크
		Member member = service.selectOneMember(m);
		if (member != null) {
			return "member/pwChangeFrm";
		} else {
			return "member/currentPwCheckFrm";
		}
	}

	@RequestMapping(value = "/pwChange.do")
	public String pwChange(Member m) {
		int result = service.changePwMember(m);
		if (result > 0) {
			return "redirect:/logout.do";
		} else {
			return "redirect:/mypage.do";
		}
	}

	@RequestMapping(value = "findPwFrm.do")
	public String findPwFrm() {
		return "member/findPwFrm";
	}

	@RequestMapping(value = "/findPw.do")
	public String findPw(Member m, Model model) {
		Member member = service.findPwMember(m);
		if (member != null) {
			model.addAttribute("m", m);
			return "member/checkEmail";
		} else {
			return "member/findPwFrm";
		}
	}

	@RequestMapping(value = "allMemberChat.do")
	public String allMemberChat() {
		return "member/allChat";
	}

	@ResponseBody
	@RequestMapping(value = "/selectAllMemberId.do", produces = "application/json;charset=utf-8")
	public String selectAllmemberId() {
		ArrayList<String> list = service.searchAllMemberId();
		return new Gson().toJson(list);
	}
}