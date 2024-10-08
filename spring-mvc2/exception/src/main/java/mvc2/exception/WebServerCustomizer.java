package mvc2.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;

//@Component // 사용안하면 주석처리
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

	@Override
	public void customize(ConfigurableWebServerFactory factory) {

		// HTTP 상태코드
		ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
		ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");

		// RuntimeException (+그 자식 타입의 예외)
		ErrorPage errorpageEx = new ErrorPage(RuntimeException.class, "/error-page/500");

		factory.addErrorPages(errorPage404, errorPage500, errorpageEx);
	}
}
