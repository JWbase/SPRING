package kr.or.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.member.model.service.MemberService;

//@Component
public class ScheduleTest {
	@Autowired
	private MemberService service;

	@Scheduled(fixedDelay = 5000)
	public void scheduleTest1() {
		System.out.println("예약작업 자동 실행 메소드!! -- 5초");
	}

	@Scheduled(fixedDelay = 10000)
	public void scheduleTest2() {
		System.out.println("10초");
	}
	// 크론식 https://zamezzz.tistory.com/197
	// http://www.cronmaker.com/ 참고

	@Scheduled(cron = "* 44 10 * * *")
	public void scheduleTest3() {
		// ex)couponService.deleteCoupon();
		// service.deleteMember("user04");
		System.out.println("크론식으로 동작하는 함수");
	}
}