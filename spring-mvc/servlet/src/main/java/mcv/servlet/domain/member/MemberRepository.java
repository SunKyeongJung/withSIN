package mcv.servlet.domain.member;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Member repository.
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }
    private MemberRepository() {
    }

    /**
     * 회원저장
     * @param member the member
     * @return the member
     */
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * 회원 단건 조회
     * @param id the id
     * @return the member
     */
    public Member findById(Long id) {
        return store.get(id);
    }

    /**
     * 전체 회원 조회
     * @return the list
     */
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * 저장소 지우기
     */
    public void clearStore() {
        store.clear();
    }

}
