package advanced.aop.proxyvs;

import advanced.aop.member.MemberService;
import advanced.aop.member.MemberServiceImpl;
import advanced.aop.proxyvs.code.ProxyDiAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDiAspect.class)
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //JDK 동적 프록시, DI 예외 발생
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"})  //CGLIB 프록시
@SpringBootTest
public class ProxyDiTest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberServiceImpl memberServiceImpl;

	@Test
	void go() {
		log.info("memberService class={}", memberService.getClass());

		log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
		memberServiceImpl.hello("hello");
	}
}
