package db1.jdbc.service;

import db1.jdbc.domain.Member;
import db1.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/**
 * 트랜잭션 - @Transactional AOP
 */
@RequiredArgsConstructor
public class MemberServiceV3_3 {
	private final MemberRepositoryV3 memberRepository;

	@Transactional
	public void accountTransfer(String fromId, String toId, int money) throws SQLException {
		//비지니스 로직
		bizLogic(fromId, toId, money);
	}

	private void bizLogic(String fromId, String toId, int money) throws SQLException {
		Member fromMember = memberRepository.findById(fromId);
		Member toMember = memberRepository.findById(toId);

		memberRepository.update(fromId, fromMember.getMoney() - money);
		validation(toMember);//오류케이스 생성
		memberRepository.update(toId, toMember.getMoney() + money);
	}

	private static void validation(Member toMember) {
		if(toMember.getMemberId().equals("ex")) {
			throw new IllegalStateException("[EXCEPTION} ::::: 이체중 예외 발생");
		}
	}
}
