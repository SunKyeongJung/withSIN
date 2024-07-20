package study.demo.autowired.allBean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.demo.AutoAppConfig;
import study.demo.discount.DiscountPolicy;
import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 타입과 관련된 모든 스프링 빈이 필요한 경우 테스트
 */
public class AllBeanTest {

    @Test
    void findAllBean() {
        // AutoAppConfig 스프링빈 다 등록하고, DiscountService 등록함
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    /**
     * 타입에 해당하는 전체 빈 조회를 위한 Service
     *   DiscountPolicy에 뭐가 들어오는지 한번에 파악이 안됨
     *   코드 추적해서 타고 들어가봐야 알 수 있음
     */
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;    // map으로 모든 DiscountPolicy(fix, rate)를 주입받음
        private final List<DiscountPolicy> policies;    // List로 DiscountPolicy타입으로 조회한 모든 스프링 빈을 주입받음

        @Autowired  // 생략가능
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policies = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            /**
             * map에 등록된 스피링 빈 중 discountCode 이름의 빈을 가져옴
             */
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
