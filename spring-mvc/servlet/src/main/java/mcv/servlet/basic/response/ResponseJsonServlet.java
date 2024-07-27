package mcv.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcv.servlet.basic.HelloData;

import java.io.IOException;

/**
 * The type Response json servlet.
 * HTTP 응답 데이터 - API JSON
 *   HTTP 응답으로 JSON을 반환할 때는 content-type을 application/json로 지정
 *   Jackson 라이브러리가 제공하는 objectMapper.writeValueAsString() 사용하면 객체를 JSON 문자로 변경 가능
 *   http://localhost:8080/response-json
 */
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("jung");
        helloData.setAge(35);

        //{"username":"jung", "age":35} : 객체 -> Json 문자
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
