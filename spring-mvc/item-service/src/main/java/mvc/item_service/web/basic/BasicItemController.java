package mvc.item_service.web.basic;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mvc.item_service.domain.item.Item;
import mvc.item_service.domain.item.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * The type Basic item controller.
 * 
 * 주의! 스프링 부트 3.2 파라미터 이름 인식 문제
 *   스프링 부트 3.2부터 자바 컴파일러에 -parameters 옵션을 넣어주어야 애노테이션의 이름 생략 가능
 *   주로 @RequestParam , @PathVariable 에서 발생 *
 *
 * HTML Form 전송은 PUT, PATCH를 지원하지 않음, GET, POST만 사용
 * PUT, PATCH는 HTTP API 전송시에 사용
 */
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam("itemName") String itemName,
                       @RequestParam("price") int price,
                       @RequestParam("quantity") Integer quantity,
                       Model model) {

//        Item item = new Item(itemName, price, quantity);
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

    /**
     * Add item v 2 string.
     * @ModelAttribute 사용
     */
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, Model model) {
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

    /**
     * Add item v 3 string.
     * @ModelAttribute("item") 이름 지정해주면 model에 "item"이라는 이름의 객체 담아줌
     *
     * @ModelAttribute
     *   Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티 접근법(setXxx)으로 입력
     *   모델(Model)에 @ModelAttribute로 지정한 객체를 자동으로 넣어줌
     */
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute("item") Item item) {
        itemRepository.save(item);

//        ModelAttribute 이름 지정으로 자동 추가됨, 생략 가능
//        model.addAttribute("item", item);

        return "basic/item";
    }

    /**
     * Add item v 4 string.
     * @ModelAttribute 이름 생략 가능
     * 모델에 저장될 때 클래스명을 사용, 클래스의 첫글자만 소문자로 변경해서 등록
     */
//    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * Add item v 5 string.
     * @ModelAttribute 어노테이션 생략 가능
     * 대상 객체는 모델에 자동 등록
     */
//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * Add item v 6 string.
     * return "basic/item" : 등록 후 상품상세 화면 내부호출
     *   마지막에 남아있는 요청이 POST /basic/items/add -> 새로고침시 중복 등록됨
     *   웹브라우저의 새로고침은 마지막에 서버에 전송한 데이터를 다시 전송
     * PRG (POST, Redirect, GET) : 리다이렉트로 상품 상세 화면 이동
     *   새로고침 GET /basic/items/{itemId}
     * URL에 변수를 더해서 사용하는 것은 URL 인코딩이 안되기 때문에 위험,,
     */
//    @PostMapping("/add")
    public String addItemV6(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    /**
     * Add item string.
     * RedirectAttributes 사용하여 URL 변수 더하는 방법 사용하지 않음
     *   URL 인코딩도 해주고, pathVariable, 쿼리 파라미터까지 처리
     */
    @PostMapping("/add")
    public String addItem(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        redirectAttributes.addAttribute("mode", "save");

        return "redirect:/basic/items/{itemId}";
        // ?status=true (url에 들어가지 않은 파라미터는 쿼리파라미터로 들어감)
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    /**
     * Edit item v 1 string.
     * 리다이렉트 호출 - redirect:/...
     * 컨트롤러에 매핑된 @PathVariable 값은 redirect에도 사용 가능
     */
//    @PostMapping("/{itemId}/edit")
    public String editItemV1(@PathVariable("itemId") long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * Edit item v 2 string.
     * RedirectAttributes 사용해서 mode 쿼리파라미터 추가
     */
    @PostMapping("/{itemId}/edit")
    public String editItemV2(@PathVariable("itemId") long itemId,
                             RedirectAttributes redirectAttributes,
                             @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        redirectAttributes.addAttribute("mode", "edit");
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
