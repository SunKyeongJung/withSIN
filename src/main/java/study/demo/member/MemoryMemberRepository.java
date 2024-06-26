package study.demo.member;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import study.demo.member.entity.Member;

import java.util.HashMap;
import java.util.Map;

/**
 * 메모리 회원저장소
 * MemberRepository 구현
 *   DB가 확정되지 않아서 메모리멤버로 사용
 */

@Component  // memoryMemberRepository
@Primary
public class MemoryMemberRepository implements MemberRepository {

    /**
     * member 저장소
     * 동시성 이슈로 ConcurrentHashMap을 사용하는게 좋음
     * 일단 개발 테스트 용도로 HashMap 사용
     */
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
