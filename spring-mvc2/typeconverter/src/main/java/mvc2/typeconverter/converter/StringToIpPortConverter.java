package mvc2.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import mvc2.typeconverter.type.IpPort;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {

    @Override
    public IpPort convert(String source) {
        //127.0.0.1:8080 -> IpPort 객체
        log.info("convert source={}", source);
        String[] split = source.split(":");
        String ip = split[0];
        Integer port = Integer.valueOf(split[1]);

        return new IpPort(ip, port);
    }
}
