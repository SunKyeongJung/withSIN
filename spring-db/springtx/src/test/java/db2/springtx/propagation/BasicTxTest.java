package db2.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.*;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class BasicTxTest {

	@Autowired
	PlatformTransactionManager txManager;

	@TestConfiguration
	static class Config {
		@Bean
		public PlatformTransactionManager transactionManager(DataSource dataSource) {
			return new DataSourceTransactionManager(dataSource);
		}
	}

	@Test
	void commit() {
		log.info("트랜잭션 시작");
		TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());

		log.info("트랜잭션 커밋 시작");
		txManager.commit(status);
		log.info("트랜잭션 커밋 완료");
	}

	@Test
	void rollback() {
		log.info("트랜잭션 시작");
		TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());

		log.info("트랜잭션 롤백 시작");
		txManager.rollback(status);
		log.info("트랜잭션 롤백 완료");
	}

	@Test
	void doubleCommit() {
		log.info("트랜잭션 11111 시작");
		TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("트랜잭션 11111 커밋");
		txManager.commit(tx1);

		log.info("트랜잭션 22222 시작");
		TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("트랜잭션 22222 커밋");
		txManager.commit(tx2);
	}

	@Test
	void doubleCommitRollback() {
		log.info("트랜잭션 11111 시작");
		TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("트랜잭션 11111 커밋");
		txManager.commit(tx1);

		log.info("트랜잭션 22222 시작");
		TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("트랜잭션 22222 롤백");
		txManager.rollback(tx2);
	}

	@Test
	void innerCommit() {
		log.info("외부 트랜잭션 시작");
		TransactionStatus outer = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

		log.info("내부 트랜잭션 시작");
		TransactionStatus inner = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("inner.isNewTransaction()={}", inner.isNewTransaction());
		log.info("내부 트랜잭션 커밋");
		txManager.commit(inner);

		log.info("외부 트랜잭션 커밋");
		txManager.commit(outer);
	}

	@Test
	void outerRollback() {
		log.info("외부 트랜잭션 시작");
		TransactionStatus outer = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

		log.info("내부 트랜잭션 시작");
		TransactionStatus inner = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("inner.isNewTransaction()={}", inner.isNewTransaction());
		log.info("내부 트랜잭션 커밋");
		txManager.commit(inner);

		log.info("외부 트랜잭션 롤백");
		txManager.rollback(outer);
	}

	@Test
	void innerRollback() {
		log.info("외부 트랜잭션 시작");
		TransactionStatus outer = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

		log.info("내부 트랜잭션 시작");
		TransactionStatus inner = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("inner.isNewTransaction()={}", inner.isNewTransaction());
		log.info("내부 트랜잭션 롤백");
		txManager.rollback(inner);

		log.info("외부 트랜잭션 커밋");
//		txManager.commit(outer);
		Assertions.assertThatThrownBy(() -> txManager.commit(outer))
				.isInstanceOf(UnexpectedRollbackException.class);
	}

	@Test
	void innerRollbackRequiersNew() {
		log.info("외부 트랜잭션 시작");
		TransactionStatus outer = txManager.getTransaction(new DefaultTransactionDefinition());
		log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

		innerLogicRollback();

		log.info("외부 트랜잭션 커밋");
		txManager.commit(outer); //커밋
	}

	private void innerLogicRollback() {
		log.info("내부 트랜잭션 시작");
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); //기존 트랜잭션 있으면 새로 만들어라(참여X)
		TransactionStatus inner = txManager.getTransaction(definition);
		log.info("inner.isNewTransaction()={}", inner.isNewTransaction()); //true

		log.info("내부 트랜잭션 롤백");
		txManager.rollback(inner); //롤백
	}
}
