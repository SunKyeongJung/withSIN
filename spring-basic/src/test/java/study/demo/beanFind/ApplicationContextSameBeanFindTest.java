package study.demo.beanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.demo.member.MemberRepository;
import study.demo.member.MemoryMemberRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 스프링빈 조회 테스트
 *   동일한 타입이 둘 이상 있는 경우
 */
public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    /**
     * 같은 타입이 둘 이상 있는 경우 테스트
     *   예외가 터져야 성공적인 테스트
     */
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복오류 발생")
    void findBeanByTeypDuplicate() {
//        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    /**
     * 같은 타입이 둘 이상 있을 때 하나만 조회
     *   빈이름 지정하여 조회
     */
    @Test
    @DisplayName("타입으로 조회시 같은타입이 둘 이상 있으면 빈 이름 지정")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    /**
     * 같은 타입이 둘 이상 있을 때 모두 조회
     */
    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    /**
     * 이 파일 내에서 사용할 Config
     *   클래스안에 새로운 클래스가 있으면,, 이 클래스는 여기에서만 사용하겠다는 의미
     *   그래서 static 사용함
     * 다른 파라미터로 빈이 생성될수도 있고,,
     *   그래서 빈의 이름이 다르고 객체 인스턴스 타입이 같을 수있음
     */
    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
