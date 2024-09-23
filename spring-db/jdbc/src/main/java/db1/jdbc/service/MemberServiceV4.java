package db1.jdbc.service;

import db1.jdbc.domain.Member;
import db1.jdbc.repository.MemberRepository;
import db1.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * 예외 누수 문제 해결
 * SQLException 제거
 * 
 * MemberRepository 인터페이스 의존
 */
@RequiredArgsConstructor
public class MemberServiceV4 {
	private final MemberRepository memberRepository;

	@Transactional
	public void accountTransfer(String fromId, String toId, int money) {
		//비지니스 로직
		bizLogic(fromId, toId, money);
	}

	private void bizLogic(String fromId, String toId, int money) {
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
