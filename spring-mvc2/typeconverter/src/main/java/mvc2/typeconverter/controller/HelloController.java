package mvc2.typeconverter.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import mvc2.typeconverter.type.IpPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

	@GetMapping("/hello-v1")
	public String helloV1(HttpServletRequest request) {
		String data = request.getParameter("data");
		Integer intData = Integer.valueOf(data);
		log.info("helloV1 intData: {}", intData);
		return "ok";
	}

	@GetMapping("/hello-v2")
	public String helloV2(@RequestParam("data") Integer data) {
		log.info("helloV2 data: {}", data);
		return "ok";
	}

	@GetMapping("/ip-port")
	public String ipPort(@RequestParam("ipPort") IpPort ipPort) {
		System.out.println("ipPort.getIp() = " + ipPort.getIp());
		System.out.println("ipPort.getPort() = " + ipPort.getPort());
		return "ok";
	}
}
