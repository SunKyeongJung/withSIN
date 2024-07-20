package study.demo.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.demo.AutoAppConfig;
import study.demo.member.MemberRepository;
import study.demo.member.MemberService;
import study.demo.order.OrderServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 의존관계 자동주입 테스트
 */
public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }

    /**
     * 필드 주입 테스트
     *   OrderServiceImpl.java 필드주입으로 변경해서 테스트 가능
     */
//    @Test
//    void fieldInjectionTest() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//
//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.setDiscountPolicy(new RateDiscountPolicy());
//
//        orderService.createOrder(1L, "itemA", 10000); // member가 없어서 오류나지만,, orderService 자체에는 문제 없음
//    }
}
