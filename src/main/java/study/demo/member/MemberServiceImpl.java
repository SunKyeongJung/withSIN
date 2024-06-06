package study.demo.member;

/**
 * 멤버서비스
 * MemberService 구현
 */
public class MemberServiceImpl implements MemberService {

    /**
     * 인터페이스 의존, 그런데 실제 할당하는 부분이 구현체를 의존
     *   둘다 의존해서 변경시 문제가 생김
     *   DIP를 위반함
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
