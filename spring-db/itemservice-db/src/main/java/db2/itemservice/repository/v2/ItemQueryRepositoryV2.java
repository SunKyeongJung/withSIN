package db2.itemservice.repository.v2;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import db2.itemservice.domain.Item;
import db2.itemservice.domain.QItem;
import db2.itemservice.repository.ItemSearchCond;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static db2.itemservice.domain.QItem.*;

@Repository
public class ItemQueryRepositoryV2 {

	private final JPAQueryFactory query;

	public ItemQueryRepositoryV2(EntityManager em) {
		this.query = new JPAQueryFactory(em);
	}

	public List<Item> findAll(ItemSearchCond cond) {
		return query.select(item)
				.from(item)
				.where(
						likeItemName(cond.getItemName()),
						maxPrice(cond.getMaxPrice())
				)
				.fetch();
	}

	private BooleanExpression likeItemName(String itemName) {
		if (StringUtils.hasText(itemName)) {
			return item.itemName.like("%" + itemName + "%");
		}
		return null;
	}

	private BooleanExpression maxPrice(Integer maxPrice) {
		if (maxPrice != null) {
			return item.price.loe(maxPrice);
		}
		return null;
	}
}
