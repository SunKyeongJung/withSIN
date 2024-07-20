package study.demo.scan.filter;

import java.lang.annotation.*;

/**
 * 이 애노테이션이 붙은거는 컴포넌트 스캔에 제외할거야
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
