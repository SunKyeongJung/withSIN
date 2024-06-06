package study.demo.discount;

import study.demo.member.Grade;
import study.demo.member.Member;

/**
 * 정액할인
 * DiscountPolicy 구현
 */
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        int discountAmount = 0;
        if (member.getGrade() == Grade.VIP) {
            if (price < discountFixAmount) {
                discountAmount = 0;
            } else {
                discountAmount = discountFixAmount;
            }
        } else {
            discountAmount = 0;
        }
        return discountAmount;
    }
}
