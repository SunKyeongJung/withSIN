package mcv.servlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcv.servlet.web.frontcontroller.ModelView;

import java.io.IOException;

/**
 * The interface My handler adapter.
 * 유연한 컨트롤러
 *   어댑터 패턴을 사용해서 프론트컨트롤러가 다양한 방식의 컨트롤러를 처리할 수 있도록 변경
 *   핸들러 어댑터: 중간에 어댑터 역할 추가
 *   핸들러: 컨트롤러의 이름을 더 넓은 범위인 핸들러로 변경
 *          어댑터 사용으로 컨트롤러 개념이 아니어도 어댑터만 있으면 처리 가능)
 *   어댑터는 실제 컨트롤러를 호출하고, 어댑터가 Model View 반환
 *   실제 컨트롤러가 ModelView를 반환하지 못하면, 어댑터가 직접 생성해서라도 반환
 *   이 어댑터를 통해 실제 컨트롤러 호출
 */
public interface MyHandlerAdapter {
    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
