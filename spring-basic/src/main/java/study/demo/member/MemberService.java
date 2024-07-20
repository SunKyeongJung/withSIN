package study.demo.member;

import study.demo.member.entity.Member;

/**
 * 회원 서비스 인터페이스
 */
public interface MemberService {
    /**
     * 회원가입
     *
     * @param member the member
     */
    void join(Member member);

    /**
     * 회원정보 찾기
     *
     * @param memberId the member id
     * @return the member
     */
    Member findMember(Long memberId);
}
