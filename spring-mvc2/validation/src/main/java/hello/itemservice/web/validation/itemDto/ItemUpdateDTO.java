package hello.itemservice.web.validation.itemDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemUpdateDTO {

	@NotNull
	private Long id;

	@NotBlank
	private String itemName;

	@NotNull
	@Range(min = 1000, max = 1000000)
	private Integer price;

	@NotNull    // 수정에서는 자유롭게 변경
	private Integer quantity;
}
