package mvc.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Log test controller.
 * @Controller -> 반환 값이 String이면 뷰 이름으로 인식
 * @RestController -> 반환 값으로 뷰를 찾는 것이 아니고, HTTP 메세지 바디에 바로 입력
 *
 * 로그 사용시 장점
 *   쓰레드 정보, 클래스 이름 같은 부가 정보를 함께 볼 수 있고, 출력 모양을 조정
 *   로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영서버에서는 출력하지 않는 등 로그를 상황에 맞게 조절
 *   시스템 아웃 콘솔에만 출력하는 것이 아니라, 파일이나 네트워크 등, 로그를 별도의 위치에 남길 수 있음
 *   파일로 남길 때는 일별, 특정 용량에 따라 로그 분할 가능
 *   성능도 일반 System.out보다 좋다. (내부 버퍼링, 멀티 쓰레드 등등)
 */
@Slf4j
@RestController
public class LogTestController {
    /**
     * @Slf4j: log 선언 없이 사용 가능 (lombok이 제공하는 기능)
     */
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

//        System.out.println("name = " + name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
