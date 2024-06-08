package study.demo.beanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.demo.AppConfig;
import study.demo.member.MemberService;
import study.demo.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 스프링 컨테이너에서 스프링 빈을 조회하는 가장 기본적인 방법
 */
public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    /**
     * 빈 이름, 인터페이스 타입으로 조회
     */
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /**
     * 빈 이름없이 타입으로 조회
     */
    @Test
    @DisplayName("빈 이름없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /**
     * 빈 이름, 구체 타입으로 조회
     *   하지만 구체타입으로 조회하는건 좋지 않음
     *   역할에 의존, 구현의 의존하지 않게 해야함,,
     *   그러나 언젠가 예상치 못한 케이스가 발생하면 사용할 수 있음
     */
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /**
     * 빈이름으로 조화: 조회결과 없는 경우
     *   NoSuchBeanDefinition ExceptionException 발생
     *   여기서 사용하는 assertThrows는 JUnit에서 제공하는 org.junit.jupiter.api.Assertions 사용
     */
    @Test
    @DisplayName("빈 이름으로 조회: 결과없음")
    void findBeanByNameX() {
//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
