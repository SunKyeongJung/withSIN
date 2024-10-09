package db2.itemservice.repository.v2;

import db2.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 스프링 데이터 JAP (JpaRepository<..>) 이거는 스프링이 자동으로 빈 등록함
 */
public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {

}
