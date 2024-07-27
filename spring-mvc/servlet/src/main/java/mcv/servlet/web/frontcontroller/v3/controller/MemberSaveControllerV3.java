package mcv.servlet.web.frontcontroller.v3.controller;

import mcv.servlet.domain.member.Member;
import mcv.servlet.domain.member.MemberRepository;
import mcv.servlet.web.frontcontroller.ModelView;
import mcv.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

/**
 * The type Member save controller v 3.
 */
public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
