package mvc.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Response view controller.
 * 뷰 템플릿 호출 Controller
 *
 * HTTP응답 : 스프링(서버)에서 응답 데이터를 만드는 방법
 *   정적 리소스 (웹브라우저에 정적인 HTML, css, js 제공) - /static, /public, /resources, /META-INF/resources
 *   뷰 템플릿 사용 (웹브라우저에 동적 HTML 제공) - 기본 뷰 템플릿 경로 : src/main/resources/templates
 *   HTTP 메세지 사용 (데이터 전달)
 */
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");

        return "response/hello";
    }

    /**
     * void 반환, HTTP 메세지 바티 처리 파라미터 없음 -> 요청 URL을 참고해서 논리 뷰 이름으로 사용
     * 권장하진 않음
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
