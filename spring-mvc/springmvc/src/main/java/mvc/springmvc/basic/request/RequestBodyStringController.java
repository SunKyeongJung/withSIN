package mvc.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * The type Request body string controller.
 * HTTP 요청 메세지 - 단순 텍스트
 *   HTTP message body에 데이터를 직접 담아서 요청
 *   HTTP API에서 주로 사용 - POST, PUT, PATCH
 *   데이터 형식 - JSON(주로 사용), XML, TEXT
 *
 * 요청 파라미터 vs HTTP 메시지 바디
 *   요청 파라미터를 조회하는 기능: @RequestParam , @ModelAttribute
 *   HTTP 메시지 바디를 직접 조회하는 기능: @RequestBody
 */
@Slf4j
@Controller
public class RequestBodyStringController {

    /**
     * Request body string v 1.
     * HttpServletRequest, HttpServletResponse 사용
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    /**
     * Request body string v 2.
     *   InputStream(Reader): HTTP요청 메세지에 바디의 내용을 직접 조회
     *   OutputStream(Writer): HTTP응답 메세지의 바디에 직접 결과 출력
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    /**
     * Request body string v 3.
     * HTTP 메세지 컨버터 사용
     *
     * HttpEntity
     *  Http header, body 정보를 편리하게 조회
     *      메세지 바디 정보를 직접 조회
     *      요청 파라미터를 조회하는 기능과 관계 없음(RequestParam/ModelAttribute - GET 쿼리파라미터, POST html 폼 전송)
     *  응답에서도 사용 가능
     *      메세지 바디 정보 직접 반환
     *      헤더정보 포함 가능
     *      view 조회X
     *  상속받은 다음 객체들고 같은 기능
     *      RequestEntity: HttpMethod, url 정보 추가, 요청에서 사용
     *      ResponseEntity: HTTP 상태코드 설정 가능, 응답에서 사용
     */
    @PostMapping("/request-body-string-v3")
//    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

//        return new HttpEntity<>("ok");
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    /**
     * Request body string v 4 http entity.
     * RequestEntity -> @RequestBody : HTTP 메세지 바디 정보 편리하게 조회, 헤더정보 필요시 HttpEntity or @RequestHeader 사용
     * ResponseEntity -> @ResponseBody : 응답 결과를 HTTP 메세지 바디에 직접 담아서 전달, view X
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
