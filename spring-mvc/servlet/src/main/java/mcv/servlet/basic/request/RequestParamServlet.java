package mcv.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * The type Request param servlet.
 * 1. HTTP 요청 데이터 - GET 쿼리 파라미터
 *   http://localhost:8080/request-param?username=hello&age=20
 * 2. HTTP 요청 데이터 - POST HTML Form
 *   요청 URL: http://localhost:8080/request-param
 *   content-type: application/x-www-form-urlencoded - GET 쿼리파라미터 형식과 같음
 *   message body: username=hello&age=20
 *
 * request.getParameter() - GET 쿼리파라미터, POST HTML From 모두 지원
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        System.out.println("[전체 파라미터 조회]");
        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username"); // 파라미터명에 대해 하나의 값만 있을 때 사용
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("name = " + name);
        }

        response.getWriter().write("ok");
    }
}
