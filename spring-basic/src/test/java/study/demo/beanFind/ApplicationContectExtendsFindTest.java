package study.demo.beanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.demo.discount.DiscountPolicy;
import study.demo.discount.FixDiscountPolicy;
import study.demo.discount.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 스프링빈 조회 - 상속관계
 */
public class ApplicationContectExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    /**
     * 부모 타입으로 조회시, 자식이 둘 이상
     *   중복오류 NoUniqueBeanDefinitionException
     */
    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면, 중복오류")
    void findBeanByParentTypeDuplication() {
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    /**
     * 부모 타입으로 조회시, 자식이 둘 이상 때 하나만 조회
     *   빈이름 지정하여 조회
     */
    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면, 빈 이름을 지정")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    /**
     * 부모 타입으로 조회시, 자식이 둘 이상 때 하나만 조회
     *   특정 하위 타입으로 조회 -> 하나밖에 없어야 에러 안남
     *   물론 안좋은 방법임
     */
    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    /**
     * 부모타입으로 조회시, 자식이 둘 이상 일때 모두 조회
     */
    @Test
    @DisplayName("부모타입으로 모두 조회하기")
    void findBeanByParentTypeAllBeanName() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    /**
     * Object타입으로 하위 bean 모두 조회
     *   스프링안에는 여러가지 빈들이 등록되어있음, 그 빈까지 조회됨
     *   자바 객체는 모든게 Object 타입이기 때문
     */
    @Test
    @DisplayName("부모타입(Object)로 모두 조회하기")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        /**
         * 리턴타입을 Rate/Fix DiscountPolicy가 아닌 DiscountPolicy로 두는 이유
         * 역할과 구현을 나누기 위함
         */
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
