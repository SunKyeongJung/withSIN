package hello.itemservice.domain.item;

/**
 * The enum Item type.
 * 상품종류: ENUM 사용, 설명을 위해 description 필드 추가
 */
public enum ItemType {

    Book("도서"), Food("음식"), ETC("기타");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
