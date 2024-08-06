package mcv.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Spring member form controller v 1.
 *
 * @Controller: HandlerAdapter
 *   스프링이 자동으로 스프링 빈으로 등록 - 내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨
 *   스프링 MVC에서 애노테이션 기반 컨트롤러로 인식
 *
 * @RequestMapping: HandlerMapping
 *   요청 정보를 매핑
 *   해당 URL이 호출되면 이 메서드가 호출
 *   애노테이션을 기반으로 동작 -> 메서드의 이름은 임의로 작성
 * ModelAndView
 *   모델과 뷰 정보를 담아서 반환
 * RequestMappingHandlerMapping
 *   스프링 빈 중에서 @RequestMapping 또는 @Controller가 클래스 레벨에 붙어 있는 경우에 매핑 정보로 인식
 *   컴포넌트 스캔 없이 스프링 빈으로 직접 등록해도 동작함
 */
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
