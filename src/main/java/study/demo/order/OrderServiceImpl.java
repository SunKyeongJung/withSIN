package study.demo.order;

import study.demo.discount.DiscountPolicy;
import study.demo.discount.FixDiscountPolicy;
import study.demo.member.Member;
import study.demo.member.MemberRepository;
import study.demo.member.MemoryMemberRepository;

/**
 * 주문서비스
 * OrderService 구현
 */
public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 단일체계 원칙 잘 지켜짐

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
