package mvc2.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mvc2.exception.exception.UserException;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		try {
			if (ex instanceof UserException) {
				log.info("UserException resolver to 400");

				String acceptHeader = request.getHeader("accept");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

				if (MediaType.APPLICATION_JSON_VALUE.equals(acceptHeader)) {
					Map<String, Object> errorResult = new HashMap<>();
					errorResult.put("ex", ex.getClass());
					errorResult.put("message", ex.getMessage());
					String sResult = objectMapper.writeValueAsString(errorResult);

					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					response.setCharacterEncoding(StandardCharsets.UTF_8.name());
					response.getWriter().write(sResult);
					return new ModelAndView();
				} else {
					// text/html
					return new ModelAndView("error/500");
				}
			}
		} catch (IOException e) {
			log.error("resolver ex", e);
		}

		return null;
	}
}
