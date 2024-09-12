package db1.jdbc.repository;

import db1.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

	MemberRepositoryV0 repository = new MemberRepositoryV0();

	@Test
	void crud() throws SQLException {
		//save
		Member member = new Member("memberV100", 10000);
		repository.save(member);

		//findById
		Member findMember = repository.findbyId(member.getMemberId());
		log.info("findMember={}", findMember);
		log.info("member == findMember {}", member == findMember);
		log.info("member.equals(findMember) {}", member.equals(findMember));
		assertThat(findMember).isEqualTo(member);

		//update: money 10000 -> 20000
		repository.update(member.getMemberId(), 20000);
		Member updatedMember = repository.findbyId(member.getMemberId());
		assertThat(updatedMember.getMoney()).isEqualTo(20000);

//		if (true) {
//			throw new IllegalStateException("....");
//		}
//		트랜젝션 X

		//delete
		repository.delete(member.getMemberId());
		assertThatThrownBy(() -> repository.findbyId(member.getMemberId()))
				.isInstanceOf(NoSuchElementException.class);
	}

}