package db2.itemservice.repository.mybatis;

import db2.itemservice.domain.Item;
import db2.itemservice.repository.ItemRepository;
import db2.itemservice.repository.ItemSearchCond;
import db2.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository {

	private final ItemMapper itemMapper;

	@Override
	public Item save(Item item) {
		log.info("itemMapper class={}", itemMapper.getClass());
		itemMapper.save(item);
		return item;
	}

	@Override
	public void update(Long itemId, ItemUpdateDto updateParam) {
		itemMapper.update(itemId, updateParam);
	}

	@Override
	public Optional<Item> findById(Long id) {
		return itemMapper.findById(id);
	}

	@Override
	public List<Item> findAll(ItemSearchCond cond) {
		return itemMapper.findAll(cond);
	}
}
