package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.SaveCheck;
import hello.itemservice.domain.item.UpdateCheck;
import hello.itemservice.web.validation.itemDto.ItemSaveDTO;
import hello.itemservice.web.validation.itemDto.ItemUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v4/items")
@RequiredArgsConstructor
public class ValidationItemControllerV4 {

	private final ItemRepository itemRepository;

	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "validation/v4/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable("itemId") long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "validation/v4/item";
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("item", new Item());
		return "validation/v4/addForm";
	}

	@PostMapping("/add")
	public String addItem(@Validated @ModelAttribute("item") ItemSaveDTO dto, BindingResult bindingResult
						, RedirectAttributes redirectAttributes, Model model) {
		//특정 필드가 아닌 복합 룰 검증
		if (dto.getPrice() != null && dto.getQuantity() != null) {
			int resultPrice = dto.getPrice() * dto.getQuantity();
			if (resultPrice < 10000) {
				bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
			}
		}

		//검증에 실패하면 다시 등록 폼으로
		if (bindingResult.hasErrors()) {
			log.info("errors = {}", bindingResult);
			return "validation/v4/addForm";
		}

		//성공 로직
		Item item = new Item();
		item.setItemName(dto.getItemName());
		item.setPrice(dto.getPrice());
		item.setQuantity(dto.getQuantity());

		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/validation/v4/items/{itemId}";
	}

	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable("itemId") Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "validation/v4/editForm";
	}

	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable("itemId") Long itemId, Model model
					, @Validated @ModelAttribute("item") ItemUpdateDTO dto, BindingResult bindingResult) {
		//특정 필드가 아닌 복합 룰 검증
		if (dto.getPrice() != null && dto.getQuantity() != null) {
			int resultPrice = dto.getPrice() * dto.getQuantity();
			if (resultPrice < 10000) {
				bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
			}
		}

		//검증에 실패하면 다시 수정 폼으로
		if (bindingResult.hasErrors()) {
			log.info("errors = {}", bindingResult);
			return "validation/v4/editForm";
		}

		//성공 로직
		Item itemParam = new Item();
		itemParam.setItemName(dto.getItemName());
		itemParam.setPrice(dto.getPrice());
		itemParam.setQuantity(dto.getQuantity());

		itemRepository.update(itemId, itemParam);
		return "redirect:/validation/v4/items/{itemId}";
	}

}
