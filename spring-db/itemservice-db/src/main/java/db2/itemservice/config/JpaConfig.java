package db2.itemservice.config;

import db2.itemservice.repository.ItemRepository;
import db2.itemservice.repository.jpa.JpaItemRepository;
import db2.itemservice.service.ItemService;
import db2.itemservice.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {

	private final EntityManager em;

	public JpaConfig(EntityManager em) {
		this.em = em;
	}

	@Bean
	public ItemService itemService() {
		return new ItemServiceV1(itemRepository());
	}

	@Bean
	public ItemRepository itemRepository() {
		return new JpaItemRepository(em);
	}
}
