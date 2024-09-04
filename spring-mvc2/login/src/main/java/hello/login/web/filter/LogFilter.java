package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
	private static final String[] whiteList = {
			"/css/*"
	};

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("== LogFilter.init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("== LogFilter.doFilter");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();

		String uuid = UUID.randomUUID().toString();

		if (isLoginCheckPath(requestURI)) {
			try {
				log.info("REQUEST [{}] [{}]", requestURI, uuid);
				chain.doFilter(request, response);
			} catch (Exception e) {
				throw e;
			} finally {
				log.info("RESPONSE [{}] [{}]", requestURI, uuid);
			}
		}
	}

	@Override
	public void destroy() {
		log.info("== LogFilter.destroy");
	}

	/**
	 * 화이트 리스트의 경우 인증 체크 X
	 */
	private boolean isLoginCheckPath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
	}
}
