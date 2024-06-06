package study.demo.discount;

import study.demo.member.entity.Member;

/**
 * 할인정책 인터페이스
 */
public interface DiscountPolicy {

    /**
     * 할인금액 조회
     *   등급조건만 있어서 member가 아닌 등급만 넘겨도 됨
     *   프로젝트에 따라 결정
     *
     * @param member the member
     * @param price  the price
     * @return 할인대상금액
     */
    int discount(Member member, int price);

}
