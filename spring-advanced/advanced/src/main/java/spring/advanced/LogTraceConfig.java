package spring.advanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.advanced.trace.logTrace.LogTrace;
import spring.advanced.trace.logTrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

	@Bean //싱글톤
	public LogTrace logTrace() {
//		return new FieldLogTrace();
		return new ThreadLocalLogTrace();
	}
}
