package mcv.servlet.web.frontcontroller.v4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcv.servlet.web.frontcontroller.ModelView;
import mcv.servlet.web.frontcontroller.MyView;
import mcv.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import mcv.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import mcv.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Front contreller servlet v 4.
 */
@WebServlet(name = "frontContrellerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontContrellerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontContrellerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontContrellerServletV4.service");

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);
        view.render(model, request, response);
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
