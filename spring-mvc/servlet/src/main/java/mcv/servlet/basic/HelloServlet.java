package mcv.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The type Hello servlet.
 *
 * @WebServlet: 서블릿 애노테이션
 *   name: 서블릿이름, urlPatterns: URL 매핑 -> 얘네들 겹치면 안됨 (중복X)
 *
 * HttpServletRequest, HttpServletResponse
 *      HTTP 요청메세지, HTTP 응답메세지를 편리하게 사용하도록 도와주는 객체
 *
 * HttpServletRequest request
 *   HTTP 요청 메세지를 통해 클라이언트에서 서버로 데이터 전달
 *   GET-쿼리 파라미터, POST-HTML Form, HTTP message body (단순텍스트, JSON)
 *   - 임시저장소 기능
 *     해당 HTTP 요청이 시작할때 부터 끝날때 까지 유지되는 임시 저장소
 *     request.setAttribute(name, value), request.getAttribute(name)
 *   - 세션관리 기능
 *     세션제공, request.getSession(create: true)
 *
 * HttpServletResponse response
 *   HTTP 응답 메시지 생성: HTTP응답코드, 헤더, 바디
 *   HTTP message body - 단순텍스트, HTML, API JSON
 *   편의기능 제공: Content-Type, 쿠키, Redirect
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        /** request */
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        /** response */
        // 헤더정보
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // write: HTTP 메세지 바디에 데이터 들어감
        response.getWriter().write("hello " + username);

    }
}
