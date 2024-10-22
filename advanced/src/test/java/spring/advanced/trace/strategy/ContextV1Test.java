package spring.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.trace.strategy.code.strategy.ContextV1;
import spring.advanced.trace.strategy.code.strategy.Strategy;
import spring.advanced.trace.strategy.code.strategy.StrategyLogic1;
import spring.advanced.trace.strategy.code.strategy.StrategyLogic2;

/**
 * Context와 Strategy 선 조립, 후 실행
 * - Context:고정, Strategy 변경
 * - 실행 시점에 이미 조립 끝남, 변경 어려움
 * - 전략을 신경쓰지 않고 단순히 실행만 하면 됨
 */
@Slf4j
public class ContextV1Test {

	@Test
	void strategyV0() {
		logic1();
		logic2();
	}

	private void logic1() {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		log.info("비즈니스 로직1 실행");
		//비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}

	private void logic2() {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		log.info("비즈니스 로직2 실행");
		//비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}

	/**
	 * 전략 패턴 사용
	 */
	@Test
	void strategyV1() {
		StrategyLogic1 strategyLogic1 = new StrategyLogic1();
		ContextV1 context1 = new ContextV1(strategyLogic1);
		context1.execute();

		StrategyLogic2 strategyLogic2 = new StrategyLogic2();
		ContextV1 context2 = new ContextV1(strategyLogic2);
		context2.execute();
	}

	/**
	 * 익명 내부클래스 사용
	 * - 클래스를 계속 만들지 않아도 됨
	 */
	@Test
	void strategyV2() {
		Strategy strategyLogic1 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		};
		ContextV1 context1 = new ContextV1(strategyLogic1);
		log.info("strategyLogic1={}", strategyLogic1.getClass());
		context1.execute();

		Strategy strategyLogic2 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		};
		ContextV1 context2 = new ContextV1(strategyLogic2);
		log.info("strategyLogic2={}", strategyLogic2.getClass());
		context2.execute();
	}

	/**
	 * 익명 내부클래스 사용 - V2 소스 inline 단축
	 */
	@Test
	void strategyV3() {
		ContextV1 context1 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});
		context1.execute();

		ContextV1 context2 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});
		context2.execute();
	}

	/**
	 * 익명 내부클래스 사용 - V3 소스 람다사용 단축
	 * 전략패턴의 인터페이스가 하나만 있는 경우 람다 사용이 가능함
	 */
	@Test
	void strategyV4() {
		ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
		context1.execute();

		ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
		context2.execute();
	}
}
