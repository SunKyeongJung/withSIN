package study.demo.order;

import study.demo.discount.DiscountPolicy;
import study.demo.discount.FixDiscountPolicy;
import study.demo.discount.RateDiscountPolicy;
import study.demo.member.entity.Member;
import study.demo.member.MemberRepository;
import study.demo.member.MemoryMemberRepository;
import study.demo.order.entity.Order;

/**
 * 주문서비스
 * OrderService 구현
 *   단일체계 원칙 잘 지켜짐
 *   할인정책 변경하려면 클라이언트인 OrderServiceImpl 고쳐야함
 *   DIP를 잘 지킨것 같지만, 구현체(fix, rate)에도 의존하고 있음
 *     DIP 위반 -> 인터페이스에만 의존하도록 변경해야함 -> null pointer exeption
 *   할인정책을 fix에서 rate로 변경(기능 확장 변경) -> OCP 위반
 */
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  // 정액
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정률

    /**
     * 인터페이스에만 의존하도록 변경 -> 생성자 추가
     *   DIP 지킴(구체화를 전혀 모르도록)
     *   의존 관계에 대한 고민은 외부(App Config)에 맡기고 실헹에만 집중
     * 권한이 줄어듬 But 책임 명확
     */
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /**
     * 테스트 용도 - AppConfig.java 싱글톤
     */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
