package study.demo.discount;

import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;

/**
 * 정률할인
 * DiscountPolicy 구현
 */
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
