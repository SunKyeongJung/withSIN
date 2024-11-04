package advanced.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

	//특정 패키지와 하위패키지
	@Pointcut("execution(* advanced.aop.order..*(..))")
	public void allOrder() {}

	//클래스 이름 패턴
	@Pointcut("execution(* *..*Service.*(..))")
	public void allService() {}

	//allOrder && allService 조합
	@Pointcut("allOrder() && allService()")
	public void orderAndService() {}
}
