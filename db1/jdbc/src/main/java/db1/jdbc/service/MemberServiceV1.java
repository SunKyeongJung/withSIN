package db1.jdbc.service;

import db1.jdbc.domain.Member;
import db1.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

@RequiredArgsConstructor
public class MemberServiceV1 {

	private final MemberRepositoryV1 memberRepository;

	public void accountTransfer(String fromId, String toId, int money) throws SQLException {
		Member fromMember = memberRepository.findbyId(fromId);
		Member toMember = memberRepository.findbyId(toId);

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
