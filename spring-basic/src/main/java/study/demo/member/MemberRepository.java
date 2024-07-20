package study.demo.member;

import study.demo.member.entity.Member;

/**
 * 회원저장소 인터페이스
 */
public interface MemberRepository {

    /**
     * 회원저장
     *
     * @param member the member
     */
    void save(Member member);

    /**
     * 아이디로 회원정보 찾기
     *
     * @param memberId the member id
     * @return the member
     */
    Member findById(Long memberId);
}
