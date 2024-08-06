package mcv.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * The type Servlet application.
 *
 * 스프링에서 서블릿을 사용하기 위해 @ServletComponentScan 지원
 *   내 패키지를 포함해서 하위패키지에 있는 서블릿을 다 찾아서 자동으로 등록, 실행
 */
@ServletComponentScan	// 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}
}
