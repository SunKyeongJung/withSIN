package study.demo;

import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;
import study.demo.member.MemberService;
import study.demo.member.MemberServiceImpl;
import study.demo.order.entity.Order;
import study.demo.order.OrderService;
import study.demo.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        // 회원등록
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // 확인
        System.out.println("order = " + order.toString());
        System.out.println("order.calulatePrice = " + order.calulatePrice());
    }
}
