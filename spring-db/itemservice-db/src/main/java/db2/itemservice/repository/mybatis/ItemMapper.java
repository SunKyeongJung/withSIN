package db2.itemservice.repository.mybatis;

import db2.itemservice.domain.Item;
import db2.itemservice.repository.ItemSearchCond;
import db2.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

	void save(Item item);

	void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateParam);

	Optional<Item> findById(Long id);

	List<Item> findAll(ItemSearchCond cond);
}
