package db2.itemservice.repository.jpa;

import db2.itemservice.domain.Item;
import db2.itemservice.repository.ItemRepository;
import db2.itemservice.repository.ItemSearchCond;
import db2.itemservice.repository.ItemUpdateDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
public class JpaItemRepository implements ItemRepository {

	private final EntityManager em;

	public JpaItemRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Item save(Item item) {
		em.persist(item);
		return item;
	}

	@Override
	public void update(Long itemId, ItemUpdateDto updateParam) {
		Item findItem = em.find(Item.class, itemId);
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
	}

	@Override
	public Optional<Item> findById(Long id) {
		Item item = em.find(Item.class, id);
		return Optional.ofNullable(item);
	}

	@Override
	public List<Item> findAll(ItemSearchCond cond) {
		String jpql = "select i from Item i";
		//Item은 객체명(대소문자 주의), i는 Item entity 자체를 의미함

		Integer maxPrice = cond.getMaxPrice();
		String itemName = cond.getItemName();

		if (StringUtils.hasText(itemName) || maxPrice != null) {
			jpql += " where";
		}

		boolean andFlag = false;
		if (StringUtils.hasText(itemName)) {
			jpql += " i.itemName like concat('%',:itemName,'%')";
			andFlag = true;
		}
		if (maxPrice != null) {
			if (andFlag) {
				jpql += " and";
			}
			jpql += " i.price <= :maxPrice";
		}
		log.info("jpql={}", jpql);

		TypedQuery<Item> query = em.createQuery(jpql, Item.class);
		if (StringUtils.hasText(itemName)) {
			query.setParameter("itemName", itemName);
		}
		if (maxPrice != null) {
			query.setParameter("maxPrice", maxPrice);
		}
		return query.getResultList();
	}
}
