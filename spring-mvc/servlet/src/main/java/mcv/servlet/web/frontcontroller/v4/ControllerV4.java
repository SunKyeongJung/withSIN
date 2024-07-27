package mcv.servlet.web.frontcontroller.v4;

import mcv.servlet.web.frontcontroller.ModelView;

import java.util.Map;

/**
 * The interface Controller v 4.
 * 조금 더 단순하고 실용적으로 개발할 수 있게 개선
 *   모델객체 전달, 뷰 논리이름 직접 반환
 */
public interface ControllerV4 {

    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
