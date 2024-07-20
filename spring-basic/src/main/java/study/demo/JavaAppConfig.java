package study.demo;

import study.demo.discount.DiscountPolicy;
import study.demo.discount.RateDiscountPolicy;
import study.demo.member.MemberRepository;
import study.demo.member.MemberService;
import study.demo.member.MemberServiceImpl;
import study.demo.member.MemoryMemberRepository;
import study.demo.order.OrderService;
import study.demo.order.OrderServiceImpl;

/**
 * 애플리케이션 환경설정/구성 (App 기획자) - 구성영역
 * 객체의 생성과 연결 담당
 *   실제 동작에 필요한 구현객체를 생성
 *   생성자를 통해서 객체 생성 -> 생성자 주입(연결), injection
 * OCP 확장에는 열려있고 변경에는 닫혀있다.
 */
public class JavaAppConfig {

    /**
     * MemoryMemberRepository 생성하고, 그 참조값을 memberServiceImpl에 주입
     * 역할, 구현이 한눈에 보여야하는데 그렇지 않고 중복도 있음
     *   리팩토링 필요 -> 역할을 세우고 구현이 그 안에 들어가도록 리팩토링
     *   메소드명, 리턴타입을 보면 역할이 보일 수 있게
     *   애플리케이션 전체 구성을 빠르게 파악 가능
     */
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 할인정책 변경 (정액 -> 정률)
     * 앱컨피그 여기만 수정하면 끝
     */
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
