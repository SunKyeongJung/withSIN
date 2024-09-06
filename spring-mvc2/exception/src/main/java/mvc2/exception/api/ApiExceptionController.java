package mvc2.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mvc2.exception.exception.BadRequestException;
import mvc2.exception.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiExceptionController {

	@GetMapping("/members/{id}")
	public MemberDto getMember(@PathVariable("id") String id) {

		if (id.equals("ex")) {
			throw new RuntimeException("잘못된 사용자");
		}
		if (id.equals("bad")) {
			throw new IllegalArgumentException("잘못된 입력 값");
		}
		if (id.equals("user-ex")) {
			throw new UserException("사용자 오류");
		}

		return new MemberDto(id, "hello " + id);
	}

	@GetMapping("/response-status-ex1")
	public String responseStatusEx1() {
		throw new BadRequestException();
	}

	@GetMapping("/response-status-ex2")
	public String responseStatusEx2() {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
	}

	@GetMapping("/default-handler-ex")
	public String defaultException(@RequestParam("data") Integer data) {
		return "ok";
	}

	@Data
	@AllArgsConstructor
	static class MemberDto {
		private String memberId;
		private String name;
	}
}
