package advanced.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

	/**
	 * 메서드의 실행의 주변에서 실행된다. 메서드 실행 전후에 작업을 수행
	 * - 가장 강력한 어드바이스
	 * - 조인 포인트 실행 여부 선택 joinPoint.proceed() 호출 여부 선택
	 * - 전달 값 변환 가능: joinPoint.proceed(args[])
	 * - 반환 값 변환 가능
	 * - 예외 변환 가능
	 * - 트랜잭션 처럼 try ~ catch~ finally 모두 들어가는 구문 처리 가능
	 * - 어드바이스의 첫 번째 파라미터는 ProceedingJoinPoint 를 사용
	 *   proceed()를 통해 대상 실행 (다음 어디바이스나 타겟)
	 *   proceed()  여러번 실행할 수도 있음(재시도) - retry
	 *
	 * JoinPoint 주요 기능
	 * - getArgs() : 메서드 인수를 반환합니다.
	 * - getThis() : 프록시 객체를 반환합니다.
	 * - getTarget() : 대상 객체를 반환합니다.
	 * - getSignature() : 조언되는 메서드에 대한 설명을 반환합니다.
	 * - toString() : 조언되는 방법에 대한 유용한 설명을 인쇄합니다
	 */
	//특정 패키지, 하위패키지 이면서 클래스 이름 패턴이 *Service
	@Around("advanced.aop.order.aop.Pointcuts.orderAndService()")
	public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			//@Before
			log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
			Object result = joinPoint.proceed();

			//@AfterReturning
			log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
			return result;
		} catch (Exception e) {
			//@AfterThrowing
			log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
			throw e;
		} finally {
			//@After
			log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
		}
	}

	/**
	 * 조인포인트 실행 전 실행
	 * - 간단한 로직에 사용, 작업 흐름 변경 X
	 */
	@Before("advanced.aop.order.aop.Pointcuts.orderAndService()")
	public void doBefore(JoinPoint joinPoint) {
		log.info(":: [before] {}", joinPoint.getSignature());
	}

	/**
	 * 메서드 실행이 정상적으로 반환될 때 실행
	 * - returning 속성에 사용된 이름과 어드바이스 메서드 매개변수 이름이 일치해야 함
	 * - return 객체는 해당 포인트컷이 걸린 메소드 반환타입과 동일해야 함
	 * - 반환되는 객체 변경은 불가능
	 */
	@AfterReturning(value = "advanced.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
	public void doReturn(JoinPoint joinPoint, Object result) {
		log.info(":: [AfterReturning] {} return={}", joinPoint.getSignature(), result);
	}

	/**
	 * 메서드 실행이 예외를 던져서 종료될 때 실행
	 * - 자동으로 throw e가 됨
	 */
	@AfterThrowing(value = "advanced.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
	public void doThrowing(JoinPoint joinPoint, Exception ex) {
		log.info(":: [AfterThrowing] {} exception=", joinPoint.getSignature(), ex);
	}

	/**
	 * 메서드 실행이 종료되면 실행
	 * - finally 같은 시점 (정상 및 예외 반환 조건 모두 처리)
	 */
	@After(value = "advanced.aop.order.aop.Pointcuts.orderAndService()")
	public void doAfter(JoinPoint joinPoint) {
		log.info(":: [After] {}", joinPoint.getSignature());
	}
}
