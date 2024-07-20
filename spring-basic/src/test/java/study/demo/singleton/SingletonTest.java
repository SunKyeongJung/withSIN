package study.demo.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.demo.AppConfig;
import study.demo.member.MemberService;

import static org.assertj.core.api.Assertions.*;

/**
 * 싱글톤 테스트
 */
public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할때매다 객체 생성
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회: 호출할때매다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /**
         * 검증 memberService1 != memberService2 (당연히 다름)
         * memberService 조회시 객체 4개가 생성됨
         *   객체를 하나만 생성하고 공유하도록 설계해야 함 (싱글톤패턴)
         */
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱클톤 패턴 적용한 객체 사용")
    void singletonServiceTest() {
//        java: SingletonService() has private access in study.demo.singleton.SingletonService 컴파일 오류 발생
//        new SingletonService();

        // 1. 조회: 호출할때매다 같은 객체 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        // 2. 조회: 호출할때매다 같은 객체 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        /**
         * 같은 객체 instance 반환 (한번 생성해 둔걸 가져다가 사용
         */
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        /**
         * 검증 memberService1 == memberService2
         */
        assertThat(singletonService1).isSameAs(singletonService2);

        // 객체 메서드 실행
        singletonService1.logic();
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회: 호출할때매다 같은 객체 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        // 2. 조회: 호출할때매다 같은 객체 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        /**
         * 같은 객체 참조
         * 스프링 처음 컨테이너에 빈 등록한거를 계속 반환
         *   MemberService에 싱글톤 관련 코드가 하나도 없는데 싱글톤 패턴 적용되어있음
         */
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /**
         * 검증 memberService1 == memberService2
         */
        assertThat(memberService1).isSameAs(memberService2);
    }
}
