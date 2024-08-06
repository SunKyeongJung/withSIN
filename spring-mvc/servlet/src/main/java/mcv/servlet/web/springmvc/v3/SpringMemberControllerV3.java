package mcv.servlet.web.springmvc.v3;

import mcv.servlet.domain.member.Member;
import mcv.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Spring member controller v 3.
 * 실용적인 방식
 *   Model 도입: 스프링 MVC도 이런 편의 기능을 제공
 *   ViewName 직접 반환: 뷰의 논리 이름을 반환
 *   @RequestParam 사용: 스프링은 HTTP 요청 파라미터를 @RequestParam 으로 받음 (GET, POST 모두 지원)
 *   @RequestMapping:
 *     @RequestMapping은 URL만 매칭하는 것이 아니라, HTTP Method도 함께 구분
 *     @RequestMapping(value = "/new-form", method = RequestMethod.GET)
 *     -> @GetMapping, @PostMapping
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }
}
