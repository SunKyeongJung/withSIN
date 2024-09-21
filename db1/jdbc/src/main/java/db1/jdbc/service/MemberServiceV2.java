package db1.jdbc.service;

import db1.jdbc.domain.Member;
import db1.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@RequiredArgsConstructor
@Slf4j
public class MemberServiceV2 {

	private final DataSource dataSource;
	private final MemberRepositoryV2 memberRepository;

	public void accountTransfer(String fromId, String toId, int money) throws SQLException {
		Connection con = dataSource.getConnection();
		try {
			//트랜잭션 시작
			con.setAutoCommit(false);
			
			//비지니스 로직
			bizLogic(con, fromId, toId, money);

			//성공시 커밋
			con.commit();
		} catch (Exception e) {
			//실패시 롤백
			con.rollback();
			throw new IllegalStateException(e);
		} finally {
			release(con);
		}
	}

	private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
		Member fromMember = memberRepository.findbyId(con, fromId);
		Member toMember = memberRepository.findbyId(con, toId);

		memberRepository.update(con, fromId, fromMember.getMoney() - money);
		validation(toMember);//오류케이스 생성
		memberRepository.update(con, toId, toMember.getMoney() + money);
	}

	private static void release(Connection con) {
		if (con != null) {
			try {
				con.setAutoCommit(true);    //커넥션 풀 고려
				con.close();
			} catch (Exception e) {
				log.info("[EXCEPTION} ::::: error", e);
			}
		}
	}

	private static void validation(Member toMember) {
		if(toMember.getMemberId().equals("ex")) {
			throw new IllegalStateException("[EXCEPTION} ::::: 이체중 예외 발생");
		}
	}
}
