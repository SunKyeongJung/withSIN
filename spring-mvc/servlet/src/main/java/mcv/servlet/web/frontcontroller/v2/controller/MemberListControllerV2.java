package mcv.servlet.web.frontcontroller.v2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcv.servlet.domain.member.Member;
import mcv.servlet.domain.member.MemberRepository;
import mcv.servlet.web.frontcontroller.MyView;
import mcv.servlet.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;
import java.util.List;

/**
 * The type Member list controller v 2.
 */
public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        return new MyView("/WEB-INF/views/members.jsp");
    }
}
