package hello.itemservice;

import hello.itemservice.web.validation.ItemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Item service application.
 *
 * Validator를 모든 컨트롤러에 다 적용해야 하는 경우 (글로벌 설정)
 * implements WebMvcConfigurer -> getValidator() override
 * 사용하는 컨트롤러 메소드에서 검증대상 파라미터 앞에 @Validated/@Valid를 넣으면 사용 가능함
 */
@SpringBootApplication
//public class ItemServiceApplication implements WebMvcConfigurer {
public class ItemServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	/*
	@Override
	public Validator getValidator() {
		return new ItemValidator();
	}
	*/
}
