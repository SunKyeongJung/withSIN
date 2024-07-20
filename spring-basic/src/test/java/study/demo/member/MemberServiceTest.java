package study.demo.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.demo.JavaAppConfig;
import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;

/**
 * 멤버서비스 테스트
 */
public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        JavaAppConfig javaAppConfig = new JavaAppConfig();
        memberService = javaAppConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
