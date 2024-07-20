package study.demo.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import study.demo.annotation.MainDiscountPolicy;
import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;

/**
 * 정률할인
 * DiscountPolicy 구현
 */
@Component
@Primary
//@Qualifier("mainDiscountPolicy")    // 이거는 문자를 잘못입력하는 경우 컴파일 오류 잡을수가 없음 (대소문자나 오타)
@MainDiscountPolicy
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
