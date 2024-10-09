package db2.springtx.apply;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class InitTxTest {

	@Autowired
	Hello hello;

	@Test
	void go () {
		//초기화 코드는 스프링이 초기화 시점에 호출한다.
//		hello.initV1();
	}


	@TestConfiguration
	static class InitTxTestConfig {
		@Bean
		Hello hello() {
			return new Hello();
		}
	}


	@Slf4j
	static class Hello {
		/**
		 * @PostConstruct, @Transactional을 함께 사용하면 트랜잭션이 적용되지 않는다.
		 * 초기화 코드 먼저 호출 -> 트랜잭션 AOP 적용
		 */
		@PostConstruct
		@Transactional
		public void initV1() {
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("Hello init @PostConstruct tx active={}", txActive);
		}

		@EventListener(ApplicationReadyEvent.class)
		@Transactional
		public void initV2() {
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("Hello init ApplicationReadyEvent tx active={}", txActive);
		}
	}
}
