package study.demo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.demo.AppConfig;
import study.demo.member.MemberRepository;
import study.demo.member.MemberServiceImpl;
import study.demo.order.OrderServiceImpl;

/**
 * Configuration 싱글톤 잘 적용되있는지 테스트
 */
public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 이렇게 구체타입으로 꺼내는건 안좋아 - 테스트용
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 세 개가 다 같은 인스턴스가 공유되어 사용됨
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
//        결과:
//        memberService -> memberRepository1 = study.demo.member.MemoryMemberRepository@4331d187
//        orderService -> memberRepository2 = study.demo.member.MemoryMemberRepository@4331d187
//        memberRepository = study.demo.member.MemoryMemberRepository@4331d187

//        AppConfig.java @Configuration 주석처리시 결과:
//        memberService -> memberRepository1 = study.demo.member.MemoryMemberRepository@404bbcbd
//        orderService -> memberRepository2 = study.demo.member.MemoryMemberRepository@1e81f160
//        memberRepository = study.demo.member.MemoryMemberRepository@1acaf3d

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        // 결과:
        // bean = class study.demo.AppConfig$$SpringCGLIB$$0

        // AppConfig.java @Configuration 주석처리 후 결과:
        // bean = class study.demo.AppConfig
    }
}
