package mcv.servlet.web.frontcontroller.v4.controller;

import mcv.servlet.domain.member.Member;
import mcv.servlet.domain.member.MemberRepository;
import mcv.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

/**
 * The type Member save controller v 4.
 */
public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);
        return "save-result";
    }
}
