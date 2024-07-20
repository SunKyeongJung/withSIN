package study.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트 스캔과 의존관계 자동주입
 *   기존의 AppConfig와는 다르게 @Bean으로 등록한 클래스가 없음
 */
@Configuration
@ComponentScan (
        /**
         * ComponentScan이 스캔으로 다 뒤져서 스프링빈으로 자동등록하는데 그중 제외할 거 지정함
         *  AppConfig 파일이 등록되면 안됨(이전 예제 유지) - 수동으로 빈 등록
         */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

        /**
         * Component 어노테이션을 탐색할 패키지의 시작 위치를 지정
         *   basePackages에 지정한 패키지부터 하위패키지를 찾아감
         *   {"study.demo.member", "study.demo.order"} 이렇게 여러개 설정 가능
        */
//        basePackages = "study.demo",

        /**
         * Component 어노테이션을 탐색할 패키지의 시작 위치를 지정
         *   지정한 클래스의 패키지를 탐색 시작 위치로 지정
         *   AutoAppConfig 클래스의 상단의 패키지 부터 찾음 (package study.demo;)
         */
//        basePackageClasses = AutoAppConfig.class
)
public class AutoAppConfig {

    /**
     * Component 애노테이션으로 설정된 빈과 같은 이름으로 수동 빈 등록
     *   빈 등록은 성공
     *   스프링부트로 실행하면 오류 발생
     */
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
