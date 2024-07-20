package study.demo.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import study.demo.discount.FixDiscountPolicy;
import study.demo.member.MemoryMemberRepository;
import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;
import study.demo.order.entity.Order;

class OrderServiceImplTest {

    /**
     * 순수 자바 코드로 테스트하기 위해 조립
     */
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "nameA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}