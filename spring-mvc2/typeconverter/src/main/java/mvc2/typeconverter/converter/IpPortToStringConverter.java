package mvc2.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import mvc2.typeconverter.type.IpPort;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        //IpPort 객체 -> 127.0.0.1:8080
        log.info("convert source={}", source);
        return source.getIp() + ":" + source.getPort();
    }
}
