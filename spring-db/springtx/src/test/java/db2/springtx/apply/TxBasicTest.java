package db2.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
public class TxBasicTest {

	@Autowired
	BasicServie basicServie;

	@Test
	void proxvyCheck() {
		log.info("aop class={}", basicServie.getClass());
		assertThat(AopUtils.isAopProxy(basicServie)).isTrue();
	}

	@Test
	void txTest() {
		basicServie.tx();
		basicServie.nonTx();
	}


	@TestConfiguration
	static class TxApplyBasicConfig {
		@Bean
		BasicServie basicServie() {
			return new BasicServie();
		}
	}


	@Slf4j
	@Transactional
	static class BasicServie {
		public void tx() {
			log.info("call tx");
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active={}", txActive);
		}

		public void nonTx() {
			log.info("call nonTx");
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active={}", txActive);
		}
	}
}
