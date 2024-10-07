package db2.itemservice.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * JPA 적용
 * -  @Entity: JPA에서 관리하는구나 알 수 있는 어노테이션
 * -  @Table: 객체명과 테이블명이 같으면 생략 가능
 */
@Data
@Entity
//@Table(name="item")
public class Item {

    @Id //테이블의 PK와 매핑되는 컬럼)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column
     * -  name : 테이블 컬럼명 매핑
     *           생략할 경우 필드의 이름을 테이블 컬럼 이름으로 사용
     *           카멜 케이스를 테이블 컬럼의 언더스코어로 자동 변환
     * -  length : DDL생성 가능 -> 그 때의 컬럼 길이 값으로 사용
     */
    @Column(name="item_name", length=10)
    private String itemName;
    private Integer price;
    private Integer quantity;

    /**
     * JPA는 public 또는 protected의 기본 생성자가 필수
     */
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
