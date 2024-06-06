package study.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;
import study.demo.member.MemberService;
import study.demo.order.entity.Order;
import study.demo.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {
        /**
         * DIP, OCP 위반
         */
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        /**
         * DIP, OCP에 맞게 수정
         */
//        JavaAppConfig javaAppConfig = new JavaAppConfig();
//        MemberService memberService = javaAppConfig.memberService();
//        OrderService orderService = javaAppConfig.orderService();

        /**
         * 스프링 기반으로 변경
         * 어노테이션 기반의 config 사용하기 위해 AnnotationConfigApplicationContext 사용
         *   AppConfig에 있는 환경설정 정보를 가지고 스프링이 스프링컨테이너에 생성해서 관리해줌
         * 컨테이너에서 이름/타입으로 특정 객체 찾음
         *   앱컨피스에서 기본적으로 메서드 이름으로 컨테이너에 등록
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        // 회원등록
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문생성
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        // 확인
        System.out.println("order = " + order.toString());
        System.out.println("order.calulatePrice = " + order.calulatePrice());
    }
}
