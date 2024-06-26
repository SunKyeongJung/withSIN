package study.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.demo.discount.DiscountPolicy;
import study.demo.discount.RateDiscountPolicy;
import study.demo.member.MemberRepository;
import study.demo.member.MemberService;
import study.demo.member.MemberServiceImpl;
import study.demo.member.MemoryMemberRepository;
import study.demo.order.OrderService;
import study.demo.order.OrderServiceImpl;

/**
 * JAVA 기반으로 생성했던 AppConfig를 스프링 기반으로 변경
 * @Configuration 애플리케이션의 구성정보/설정정보를 담당
 * 각 메소드에 @Bean 추가 -> 스프링컨테이너에 등록됨
 */
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository(), new RateDiscountPolicy()
    // 각각 다른 두개의 MemoryMemberRepository 호출,, 이러면 싱글톤이 깨지는거 아닌가?

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
