package db2.itemservice.config;

import db2.itemservice.repository.ItemRepository;
import db2.itemservice.repository.mybatis.ItemMapper;
import db2.itemservice.repository.mybatis.MyBatisItemRepository;
import db2.itemservice.service.ItemService;
import db2.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

	private final ItemMapper itemMapper;

	@Bean
	public ItemService itemService() {
		return new ItemServiceV1(itemRepository());
	}

	@Bean
	public ItemRepository itemRepository() {
		return new MyBatisItemRepository(itemMapper);
	}
}
