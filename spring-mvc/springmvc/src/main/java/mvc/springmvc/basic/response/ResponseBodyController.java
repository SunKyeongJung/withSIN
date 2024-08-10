package mvc.springmvc.basic.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mvc.springmvc.basic.HelloData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * The type Response body controller.
 * HTTP 메시지 바디에 JSON 같은 형식으로 데이터를 실어 보냄
 *
 * String을 반환 - View or HTTP 메시지
 *   @ResponseBody 가 없으면 response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링
 *   @ResponseBody 가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello 라는 문자 입력
 * Void를 반환
 *   @Controller를 사용하고, HttpServletResponse/OutputStream 같은 HTTP 메시지 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용
 *   명시성이 너무 떨어지고 이렇게 딱 맞는 경우도 많이 없어서, 권장하지 않음
 *
 * @Controller 대신 @RestController 애노테이션을 사용 -> 해당 컨트롤러에 모두 @ResponseBody가 적용되는 효과
 */
@Slf4j
@Controller
//@ResponseBody // 클래스 단위에 불일 수 있음
//@RestContoller // @Controlelr + @ResponseBody
public class ResponseBodyController {

    /**
     * Response body string v 1.
     * HttpServletResponse 객체를 통해서 HTTP 메시지 바디에 직접 ok 응답 메시지를 전달
     */
    @GetMapping("/response-body-string-v1")
    public void responseBodyStringV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * Response body string v 2.
     * ResponseEntity로 HTTP 메세지 헤더, 바디정보 조회 가능, HTTP 응답코드 설정 ok
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyStringV2() throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * Response body string v 3.
     * @ResponseBody : view를 사용하지 않고, HTTP 메시지 컨버터를 통해서 HTTP 메시지를 직접 입력 (ResponseEntity 도 동일)
     */
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyStringV3() {
        return "ok";
    }

    /**
     * Response body json v 1 response entity.
     * ResponseEntity 반환, HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환되어서 반환
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * Response body json v 2 response entity.
     * @ResponseBody 사용, HTTP응답코드 설정이 까다로움
     * @ResponseStatus(HttpStatus.OK) 애노테이션을 : 응답코드 설정 가능, 단 동적으로 상태 변경하려면 ResponseEntity 사용해야함
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }
}
