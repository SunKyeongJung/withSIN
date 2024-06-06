package study.demo;

import study.demo.discount.FixDiscountPolicy;
import study.demo.member.MemberService;
import study.demo.member.MemberServiceImpl;
import study.demo.member.MemoryMemberRepository;
import study.demo.order.OrderService;
import study.demo.order.OrderServiceImpl;

/**
 * 애플리케이션 환경설정/구성 (App 기획자)
 * 객체의 생성과 연결 담당
 *   실제 동작에 필요한 구현객체를 생성
 *   생성자를 통해서 객체 생성 -> 생성자 주입(연결), injection
 */
public class AppConfig {

    /**
     * MemoryMemberRepository 생성하고, 그 참조값을 memberServiceImpl에 주입
     */
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
