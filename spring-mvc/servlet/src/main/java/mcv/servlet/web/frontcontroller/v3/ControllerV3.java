package mcv.servlet.web.frontcontroller.v3;

import mcv.servlet.web.frontcontroller.ModelView;

import java.util.Map;

/**
 * The type Controller v 3.
 * Model 추가
 *   서블릿 종속성 제거 - 컨트롤러에  HttpServletRequest, HttpServletResponse 불필요
 *   뷰 이름 중복 제거 - /WEB-INF/views/....jsp (물리 이름이 아닌 논리 이름으로 변경)
 */
public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
