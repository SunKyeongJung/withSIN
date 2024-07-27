package mcv.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The type Request body string servlet.
 * HTTP 요청 데이터 - API 메시지 바디 - 단순 텍스트
 *  HTTP message body에 데이터를 직접 담아서 요청
 *  POST http://localhost:8080/request-body-string
 *  content-type: text/plain
 *  message body: hello
 */
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();  // 바디의 내용을 바이트 코드로 바로 가져옴
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 바디 <-> 문자 : 어떤 인코딩 사용할지 지정해줘야함

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
