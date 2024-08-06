package mcv.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * The type Old controller.
 * - 과거 버전 스프링 컨트롤러: 스프링이 제공하는 간단한 컨트롤러로 @Controller와 다름
 * - @Compnent 스프링 빈 이름 설정
 *
 * 이 컨트롤러가 호출되려면
 *   HandlerMapping(핸들러 매핑): 스프링 빈 이름으로 핸들러를 찾을 수 있는 핸들러 매핑 필요
 *   HandlerAdapter(핸들러 어댑터): 핸들러 매핑을 통해서 찾은 핸들러를 실행할 수 있는 어댑터 필요
 */
@Component("/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
