package study.demo.order;

import study.demo.order.entity.Order;

/**
 * 주문서비스 인터페이스
 */
public interface OrderService {

    /**
     * 주문생성
     *
     * @param memberId  the member id
     * @param itemName  the item name
     * @param itemPrice the item price
     * @return 주문결과
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
