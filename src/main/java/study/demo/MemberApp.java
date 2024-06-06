package study.demo;

import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;
import study.demo.member.MemberService;
import study.demo.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        // 회원가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        // 회원정보 찾기
        Member findMember = memberService.findMember(1L);

        // 확인
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
