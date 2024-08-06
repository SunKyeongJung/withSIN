package mcv.servlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcv.servlet.web.frontcontroller.ModelView;
import mcv.servlet.web.frontcontroller.MyView;
import mcv.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import mcv.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import mcv.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import mcv.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import mcv.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import mcv.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import mcv.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import mcv.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Front controller servlet v 5.
 * 유연한 컨트롤러 V3에 V4도 추가
 */
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handelerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandelerMappingMap();
        initHandlerAdapters();
    }

    /**
     * 핸들러 매핑 초기화
     */
    private void initHandelerMappingMap() {
        // V3
        handelerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handelerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handelerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // V4
        handelerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handelerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handelerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    /**
     * 어댑터 초기화
     */
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontContrellerServletV5.service");

        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter myAdapter = getHandlerAdapter(handler);
        ModelView mv = myAdapter.handle(request, response, handler);

        String viewName = mv.getViewName();// 논리이름 new-form
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    /**
     * 핸들러 매핑
     */
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handelerMappingMap.get(requestURI);
    }

    /**
     * 핸들러를 처리할 수 있는 어댑터 조회
     */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    /**
     * view 논리이름으로 물리이름 반환
     */
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}

