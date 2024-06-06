package study.demo.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.demo.JavaAppConfig;
import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;
import study.demo.member.MemberService;
import study.demo.order.entity.Order;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        JavaAppConfig javaAppConfig = new JavaAppConfig();
        memberService = javaAppConfig.memberService();
        orderService = javaAppConfig.orderService();
    }

    @Test
    void createOrder() {
        // 회원등록
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // 확인
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
