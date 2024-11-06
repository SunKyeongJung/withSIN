package advanced.aop.pointcut;

import advanced.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * public java.lang.String advanced.aop.member.MemberServiceImpl.hello(java.lang.String)
 */
@Slf4j
public class ExecutionTest {

	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMethod;

	@BeforeEach
	public void init() throws NoSuchMethodException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}

	@Test
	void printMethod() {
		//public java.lang.String advanced.aop.member.MemberServiceImpl.hello(java.lang.String)
		log.info("helloMethod={}", helloMethod);
	}

	@Test
	@DisplayName("정확하게 맞는 exact match execution test")
	void exactMatch() {
		pointcut.setExpression("execution(public String advanced.aop.member.MemberServiceImpl.hello(String))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("가장 많이 생략한 match execution test")
	void allMatch() {
		pointcut.setExpression("execution(* *(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("메서드 이름 match execution test")
	void nameMatch() {
		pointcut.setExpression("execution(* hello(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("메서드 이름 뒤쪽 pattern match execution test")
	void nameMatchStar1() {
		pointcut.setExpression("execution(* hel*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("메서드 이름 앞쪽 pattern match execution test")
	void nameMatchStar2() {
		pointcut.setExpression("execution(* *el*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("메서드 이름 pattern match false execution test")
	void nameMatchFalse() {
		pointcut.setExpression("execution(* nono(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	@DisplayName("패키지 정확 match execution test")
	void packageExactMatch1() {
		pointcut.setExpression("execution(* advanced.aop.member.MemberServiceImpl.hello(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("패키지 pattern match execution test")
	void packageMatchStar() {
		pointcut.setExpression("execution(* advanced.aop.member.*.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("패키지 pattern match false execution test")
	void packageMatchFalse() {
		pointcut.setExpression("execution(* advanced.aop.*.*(..))");    //depth가 맞아야함
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	@DisplayName("패키지 pattern match (include sub) execution test")
	void packageMatchSubPackage1() {
		pointcut.setExpression("execution(* advanced.aop..*.*(..))");   //depth가 안맞아도 되려면 sub를 포함하는 .. 사용
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("타입 Exact 매칭")
	void typeExactMatch() {
		pointcut.setExpression("execution(* advanced.aop.member.MemberServiceImpl.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("부모 타입 매칭")
	void typetMatchSuperType() {
		pointcut.setExpression("execution(* advanced.aop.member.MemberService.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("부모 타입에 없는 타입 매칭")
	void typetMatchInternal() throws NoSuchMethodException {
		pointcut.setExpression("execution(* advanced.aop.member.MemberServiceImpl.*(..))");

		Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
		assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("부모 타입에 없는 부모타입 매칭 fasle")
	void typetMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
		pointcut.setExpression("execution(* advanced.aop.member.MemberService.*(..))");

		Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
		assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	@DisplayName("파라미터 매칭 - 스트링 허용")
	void argsMatch() {
		pointcut.setExpression("execution(* *(String))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("파라미터 매칭 - 파라미터 없음")
	void argsMatchNoArgs() {
		pointcut.setExpression("execution(* *())");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	@DisplayName("파라미터 매칭 - 모든 타입 파라미터 한개만 허용(Xxx)")
	void argsMatchStar() {
		pointcut.setExpression("execution(* *(*))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("파라미터 매칭 - 갯수와 무관하게 모든 타입 파라미터 허용 (), (Xxx), (Xxx, Xxx)")
	void argsMatchAll() {
		pointcut.setExpression("execution(* *(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("파라미터 매칭 - String으로 시작, 갯수와 무관하게 모든 타입 파라미터 허용 (String), (String, Xxx), (String, Xxx, Xxx)")
	void argsMatchComplex() {
		pointcut.setExpression("execution(* *(String, ..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
}
