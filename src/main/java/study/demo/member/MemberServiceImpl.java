package study.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.demo.member.entity.Member;

/**
 * 멤버서비스
 * MemberService 구현
 */
@Component
public class MemberServiceImpl implements MemberService {

    /**
     * 인터페이스 의존, 그런데 실제 할당하는 부분이 구현체를 의존
     *   둘다 의존해서 변경시 문제가 생김
     *   DIP를 위반함
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * Repository 값을 할당하지 않고, 생성자 추가
     *   그러면 여기서는 MemoryMemberRepository 사용하지 않음
     *   MemberRepository 인터페이스만 있음
     *     추상화에만 의존함 -> DIP 지킴
     *     생성자를 통해서 객체 생성 -> 생성자 주입
     * 외부에서 주입해주는것 같다고 해서 DI(의존관계 주입)
     */
    private final MemberRepository memberRepository;

    /**
     * AppConfig에서는 의존관계를 직접 주입하여 사용
     * 의존관계 자동주입을 해주는 AutoWired 사용
     */
    @Autowired  // ac.getBean(MemberRepository.class) 기능과 같음
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /**
     * 테스트 용도 - AppConfig.java 싱글톤
     */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
