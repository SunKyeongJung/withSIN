package study.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.demo.member.entity.Grade;
import study.demo.member.entity.Member;
import study.demo.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {
        /**
         * DIP, OCP 위반
         */
//        MemberService memberService = new MemberServiceImpl();

        /**
         * DIP, OCP에 맞게 수정
         */
//        JavaAppConfig javaAppConfig = new JavaAppConfig();
//        MemberService memberService = javaAppConfig.memberService();

        /**
         * 스프링 기반으로 변경
         * 어노테이션 기반의 config 사용하기 위해 AnnotationConfigApplicationContext 사용
         *   AppConfig에 있는 환경설정 정보를 가지고 스프링이 스프링컨테이너에 생성해서 관리해줌
         * 컨테이너에서 이름/타입으로 특정 객체 찾음
         *   앱컨피스에서 기본적으로 메서드 이름으로 컨테이너에 등록
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);   // 이름, 반환타입

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
