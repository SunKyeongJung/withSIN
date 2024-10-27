package spring.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.trace.strategy.code.strategy.ContextV2;
import spring.advanced.trace.strategy.code.strategy.Strategy;
import spring.advanced.trace.strategy.code.strategy.StrategyLogic1;
import spring.advanced.trace.strategy.code.strategy.StrategyLogic2;

/**
 * Context를 실행할 때 마다 Stretegy를 인수로 전달
 * - Context:고정, Strategy 변경
 * - 실행할 때 마다 전략 유연하게 변경 가능
 * - 실행할 때 마다 전략 계속 지정해줘야함 (장점이자 단점)
 */
@Slf4j
public class ContextV2Test {

	/**
	 * 전략 패턴 적용
	 */
	@Test
	void strategyV1() {
		ContextV2 context = new ContextV2();
		context.execute(new StrategyLogic1());
		context.execute(new StrategyLogic2());
	}

	/**
	 * 전략 패턴 익명 내부 클래스
	 */
	@Test
	void strategyV2() {
		ContextV2 context = new ContextV2();

		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});
		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});
	}

	/**
	 * 전략 패턴 익명 내부 클래스 - 람다
	 */
	@Test
	void strategyV3() {
		ContextV2 context = new ContextV2();

		context.execute(() -> log.info("비즈니스 로직1 실행"));
		context.execute(() -> log.info("비즈니스 로직2 실행"));
	}
}