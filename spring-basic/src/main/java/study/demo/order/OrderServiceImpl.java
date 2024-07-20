package study.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.demo.annotation.MainDiscountPolicy;
import study.demo.discount.DiscountPolicy;
import study.demo.member.MemberRepository;
import study.demo.member.entity.Member;
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
@Component
//@RequiredArgsConstructor
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
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * 생성자주입
     *   생성자가 하나면 @Autowired 생략해도됨
     * lombok@RequiredArgsConstructor 사용하면 final 붙은거로 생성자 만들어줘서 생성자주입 따로 안해도 됨
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 의존관계 주입 - 수정자 주입
     *   이거로 주입하면 생성자 없어도 된다
     */
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 의존관계 주입-필드주입 방법
     *   값을 넣어줄 방법이 없음
     *   setter 만들어서 값 넣어줘야함
     *   어차피 setter를 만들어야하면 그냥 setter에 Autowired 하는게 나음
     */
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private DiscountPolicy discountPolicy;

    /**
     * 일반메서드 주입
     *   수정자주입하고 사실 똑같아,,
     */
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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
