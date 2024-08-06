package mvc.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The type Mapping controller.
 */
@Slf4j
@RestController
public class MappingController {

    /**
     * 기본 요청
     * /hello-basic, /hello-basic/ : 스프링부트 3.0 이전은 둘다 허용, 이후는 다르게 매핑
     * HTTP 메서드 모두 허용 (GET, HEAD, POST, PUT, PATCH, DELETE)
     *
     * @RequestMapping
     *   URL 호출이 오면 이 메서드가 실행되도록 매핑
     *   대부분의 속성을 배열[]로 제공하므로 다중 설정이 가능 {"/hello-basic", "/hello-go"}
     */
    @RequestMapping(value = "/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * method 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용 (/mapping/userA)
     * 변수명이 같으면 생략 가능
     *   @PathVariable("userId") String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
//    public String mappingPath(@PathVariable("userId") String data) {
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑 (거의 사용안함)
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     * 예시) consume: 요청헤더의 content type
     *   consumes = "text/plain"
     *   consumes = {"text/plain", "application/*"}
     *   consumes = MediaType.TEXT_PLAIN_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     * 예시) produce: 요청 헤더의 accept
     *   produces = "text/plain"
     *   produces = {"text/plain", "application/*"}
     *   produces = MediaType.TEXT_PLAIN_VALUE
     *   produces = "text/plain;charset=UTF-8"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
