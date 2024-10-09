package db2.itemservice.config;

import db2.itemservice.repository.ItemRepository;
import db2.itemservice.repository.jpa.JpaItemRepositoryV3;
import db2.itemservice.repository.jpa.SpringDataJpaItemRepository;
import db2.itemservice.service.ItemService;
import db2.itemservice.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QuerydslConfig {

	private final EntityManager em;

	private final SpringDataJpaItemRepository springDataJpaItemRepository;

	@Bean
	public ItemService itemService() {
		return new ItemServiceV1(itemRepository());
	}

	@Bean
	public ItemRepository itemRepository() {
		return new JpaItemRepositoryV3(em);
	}
}
