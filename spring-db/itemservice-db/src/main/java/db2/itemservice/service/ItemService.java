package db2.itemservice.service;

import db2.itemservice.repository.ItemSearchCond;
import db2.itemservice.repository.ItemUpdateDto;
import db2.itemservice.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findItems(ItemSearchCond itemSearch);
}
