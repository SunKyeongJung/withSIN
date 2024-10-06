package db2.itemservice.config;

import db2.itemservice.repository.ItemRepository;
import db2.itemservice.repository.jdbcTemplate.JdbcTemplateItemRepositoryV3;
import db2.itemservice.service.ItemService;
import db2.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateV3Config {

	private final DataSource dataSource;

	@Bean
	public ItemService itemService() {
		return new ItemServiceV1(itemRepository());
	}

	@Bean
	public ItemRepository itemRepository() {
		return new JdbcTemplateItemRepositoryV3(dataSource);
	}
}
