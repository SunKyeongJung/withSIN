package mcv.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Model view.
 *   HttpServletRequest 사용 X, request.setAttibuty() 호출 X -> Model이 별도로 필요
 */
@Getter @Setter
public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
