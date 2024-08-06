package mvc.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * The type Request header controller.
 *
 * @Controller 의 사용 가능한 파라미터 목록은 다음 공식 메뉴얼에서 확인할 수 있다.
 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-annarguments
 *
 * @Controller 의 사용 가능한 반환 값 목록은 다음 공식 메뉴얼에서 확인할 수 있다.
 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-annreturn-types
 */
@Slf4j
@RestController
public class RequestHeaderController {

    /**
     * @param request       HttpServletRequest
     * @param response      HttpServletResponse
     * @param httpMethod    HTTP 메서드를 조회 (org.springframework.http.HttpMethod)
     * @param locale        Locale 정보를 조회
     * @param headerMap     MultiValueMap: Map과 유사한데, 하나의 키에 여러 값 받음 (keyA=value1&keyA=value2)
     *                      HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용     *
     * @param host          특정 HTTP 헤더를 조회 (host)
     * @param cookie        특정 쿠키를 조회
     *                      필수 값 여부: required
     *                      기본 값: defaultValue
     */
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
    ) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }

}
