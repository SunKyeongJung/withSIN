package study.demo;

import study.demo.member.Grade;
import study.demo.member.Member;
import study.demo.member.MemberService;
import study.demo.member.MemberServiceImpl;
import study.demo.order.Order;
import study.demo.order.OrderService;
import study.demo.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderServcie = new OrderServiceImpl();

        // 회원등록
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문생성
        Order order = orderServcie.createOrder(memberId, "itemA", 10000);

        // 확인
        System.out.println("order = " + order.toString());
        System.out.println("order.calulatePrice = " + order.calulatePrice());
    }
}
