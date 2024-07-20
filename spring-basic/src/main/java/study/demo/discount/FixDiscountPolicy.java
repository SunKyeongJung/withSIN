package study.demo.discount;

import org.springframework.stereotype.Component;
import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;

/**
 * 정액할인
 * DiscountPolicy 구현
 */
@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
