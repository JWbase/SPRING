package kr.or.iei.controller;

import javax.servlet.http.HttpServletRequest;

public class SearchController implements Controller {

	@Override
	public String request(HttpServletRequest request) {

		//2. 값추출
		String memberId = request.getParameter("memberId");
		//3. 비즈니스로직
		//user01과 일치하면 조회성공 / 실패
		boolean result = memberId.equals("user01");

		if (result) {
			return "searchSuccess";
		} else {
			return "searchFail";
		}
	}
}