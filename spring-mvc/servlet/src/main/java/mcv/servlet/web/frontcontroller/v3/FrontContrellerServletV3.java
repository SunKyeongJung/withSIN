package mcv.servlet.web.frontcontroller.v3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcv.servlet.web.frontcontroller.ModelView;
import mcv.servlet.web.frontcontroller.MyView;
import mcv.servlet.web.frontcontroller.v2.ControllerV2;
import mcv.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import mcv.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import mcv.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Front contreller servlet v 3.
 */
@WebServlet(name = "frontContrellerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontContrellerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontContrellerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontContrellerServletV3.service");

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();// 논리이름 new-form
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    /**
     * view 논리이름으로 물리이름 반환
     */
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    /**
     * request 파라미터를 Map<String, String> 형태로 반환
     */
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
