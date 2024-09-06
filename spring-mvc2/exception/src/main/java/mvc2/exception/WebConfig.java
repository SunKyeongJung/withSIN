package mvc2.exception;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import mvc2.exception.filter.LogFilter;
import mvc2.exception.interceptor.LogInterceptor;
import mvc2.exception.resolver.MyHandlerExceptionResolver;
import mvc2.exception.resolver.UserHandlerExceptionResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
<<<<<<< HEAD
import mvc2.exception.filter.LogFilter;
import mvc2.exception.interceptor.LogInterceptor;
=======

import java.util.List;
>>>>>>> origin/main

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor())
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/css/**", "*.ico", "/error", "/error-page/**");
	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(new MyHandlerExceptionResolver());
		resolvers.add(new UserHandlerExceptionResolver());
	}

	//	@Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LogFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		//기본값 DispatcherType.REQUEST
		filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
		return filterRegistrationBean;
	}
}
