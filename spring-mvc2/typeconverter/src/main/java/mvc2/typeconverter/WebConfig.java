package mvc2.typeconverter;

import mvc2.typeconverter.converter.IntegerToStringConverter;
import mvc2.typeconverter.converter.IpPortToStringConverter;
import mvc2.typeconverter.converter.StringToIntegerConverter;
import mvc2.typeconverter.converter.StringToIpPortConverter;
import mvc2.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
//		주석처리 우선순위 (컨버터 > 포멧터)
//		registry.addConverter(new StringToIntegerConverter());
//		registry.addConverter(new IntegerToStringConverter());
		registry.addConverter(new StringToIpPortConverter());
		registry.addConverter(new IpPortToStringConverter());

		//포멧터 추가
		registry.addFormatter(new MyNumberFormatter());
	}
}
