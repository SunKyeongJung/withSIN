package study.demo.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 싱글톤 방식의 중요성!! - 테스트
 *   설명을 위해 실제 Thread를 사용하지 않음, 실제로는 더 복잡
 */
class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        /**
         * 공유필드를 사용하는 문제 예시
         */
//        // ThreadA: A사용자가 10000원 주문
//        statefulService1.order("userA", 10000);
//        // ThreadB: B사용자가 20000원 주문 (A가 주문하고 금액조회하는 사이에 B 주문이 끼어듬)
//        statefulService2.order("userB", 20000);
//
//        // ThreadA: 사용자 A 주문금액 조회 -> 사용자 B의 금액이 조회됨 -> 망함!!
//        int price = statefulService1.getPrice();
////        System.out.println("price = " + price);
//
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);



        int userAPrice = statefulService1.order("userA", 10000);
        int userBPrice = statefulService2.order("userB", 20000);
        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

    }

    /**
     * stateful 전용 config
     */
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}