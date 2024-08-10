package mvc.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mvc.springmvc.basic.HelloData;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The type Request body json controller.
 * HTTP API에는 주로 JSON 사용
 *
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMepper = new ObjectMapper();

    /**
     * Request body json v 1.
     * HttpServletRequest, HttpServletResponse 사용
     */
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);

        HelloData helloData = objectMepper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    /**
     * Request body json v 2.
     * @RequestBody, @ResponseBody 사용
     * @RequestBody 생략 불가능(@RequestParam 또는 @ModelAttribute가 적용되어 버림)
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);

        HelloData helloData = objectMepper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    /**
     * Request body json v 3 string.
     * @RequestBody 객체 파라미터 사용 (직접 만든 객체 지정 가능)
     *
     * HttpEntity , @RequestBody -> HTTP메세지 컨버터가 HTTP 메세지 바디의 내용을 우리가 원하는 문나 객체 등으로 변환
     * HTTP메시지 컨버터는 문자 뿐만 아니라 JSON도 객체로 변환해줌
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * Request body json v 4 string.
     * HttpEntity 사용
     */
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    /**
     * Request body json v 5 string.     *
     * 응답의 경우에도 @ResponseBody에 객체를 넣어 줄 수 있음 (HttpEntity 사용가능)
     *
     * @RequestBody 요청 : JSON요청 -> HTTP메세지컨버터 -> 객체 (content-type: application/json)
     * @ResponseBody 응답 : 객체 -> HTTP메세지컨버터 -> JSON응답 (Accept: application/json)
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }
}
