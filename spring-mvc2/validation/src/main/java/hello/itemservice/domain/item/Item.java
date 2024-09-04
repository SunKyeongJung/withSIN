package hello.itemservice.domain.item;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * The type Item.
 *
 * 오브젝트 오류 @ScriptAssert 사용해서 처리 가능, 권장하지 않음
 * 오브젝트 오류만 java코드로 사용하는 것을 권장함
 *
 * 컨트롤러에서 @validated(SaveCheck.class)처럼 groups 사용하
 * 등록/수정시 다르게 validation 적용 가능
 * 복잡도가 높음,, 잘 사용하지 않음 -> 주석처리
 * 
 * Form 전송 객체 분리 사용
 */
@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총 합이 10,000원 이상이어야 합니다")
public class Item {

//    @NotNull(groups = UpdateCheck.class) //수정 요구사항 추가
    private Long id;

//    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

//    @NotNull
//    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 9999, groups = {SaveCheck.class}) //수정 요구사항 추가
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
