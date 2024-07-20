package study.demo.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP등급 10% Discount")
    void grade_vip() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("일반등급 No Discount")
    void grade_basic() {
        //given
        Member member = new Member(2L, "memberBasic", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
//        Assertions.assertThat(discount).isEqualTo(1000);
        assertThat(discount).isEqualTo(0);
    }

}