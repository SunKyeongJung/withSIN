package hello.itemservice.web.validation;

import hello.itemservice.web.validation.itemDto.ItemSaveDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Validation item api controller.
 * Bean Validation - HTTP 메시지 컨버터
 *
 * @ModelAttribute (HTTP요청 파라미터 처리)
 *   각각의 필드 단위로 적용, 특정 필드에 타입이 맞지 않는 오류가 발생해도 나머지 필드는 정상처리 가능
 * @RequestBoddy (HttpMessageConverter)
 *   전체 객체 단위로 적용, 모든 필드가 정상처리 되야 validation 적용됨
 */
@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

	@PostMapping("/add")
	public Object addItem(@RequestBody @Validated ItemSaveDTO dto, BindingResult bindingResult) {
		log.info("API 컨트롤러 호출");

		if (bindingResult.hasErrors()) {
			log.info("검증 오류 발생 errors={}", bindingResult);
			return bindingResult.getAllErrors();
		}

		log.info("성공 로직 실행");
		return dto;
	}
}
