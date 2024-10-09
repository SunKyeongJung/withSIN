package db2.itemservice.config;

import db2.itemservice.repository.ItemRepository;
import db2.itemservice.repository.jpa.JpaItemRepositoryV2;
import db2.itemservice.repository.jpa.JpaItemRepositoryV3;
import db2.itemservice.repository.jpa.SpringDataJpaItemRepository;
import db2.itemservice.repository.v2.ItemQueryRepositoryV2;
import db2.itemservice.repository.v2.ItemRepositoryV2;
import db2.itemservice.service.ItemService;
import db2.itemservice.service.ItemServiceV1;
import db2.itemservice.service.ItemServiceV2;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class V2Config {

	private final EntityManager em;
	private final ItemRepositoryV2 itemRepositoryV2;    //SpringDataJPA

	@Bean
	public ItemService itemService() {
		return new ItemServiceV2(itemRepositoryV2, itemQueryRepositoryV2());
	}

	@Bean
	public ItemQueryRepositoryV2 itemQueryRepositoryV2() {
		return new ItemQueryRepositoryV2(em);
	}

	@Bean
	public ItemRepository itemRepository() {    //테스트에서 사용중이라 남겨둠
		return new JpaItemRepositoryV3(em);
	}
}
