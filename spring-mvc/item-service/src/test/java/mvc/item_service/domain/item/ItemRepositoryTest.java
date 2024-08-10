package mvc.item_service.domain.item;

import mvc.item_service.domain.item.Item;
import mvc.item_service.domain.item.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * The type Item repository test.
 * 아이템 저장소 테스트
 */
class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item saveedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(saveedItem.getId());
        assertThat(findItem).isEqualTo(saveedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);
        Item item3 = new Item("itemC", 15000, 15);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains(item1, item2, item3);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("itemA", 10000, 10);
        Item saveedItem = itemRepository.save(item);
        Long itemId = saveedItem.getId();

        //when
        Item updateParam = new Item("itemB", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}