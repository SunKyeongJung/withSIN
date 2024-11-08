package advanced.aop.proxyvs;

import advanced.aop.member.MemberService;
import advanced.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class ProxyCastingTest {

	@Test
	void jdkProxy() {   //인터페이스 기반으로 프록시 생성
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(false); //JDK 동적 프록시

		//프록시를 인터페이스로 캐스팅 성공
		MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

		//JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 예외 발생
		assertThrows(ClassCastException.class, () -> {
			MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
		});
	}

	@Test
	void cglibProxy() { //impl 기반으로 프록시 생성
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(true); //CGLIB 프록시

		//프록시를 인터페이스로 캐스팅 성공
		MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

		//CGLIB 프록시를 구현 클래스로 캐스팅 시도 성공, ClassCastException 예외 발생
		MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
	}
}
