package mvc2.upload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc2.upload.domain.Item;
import mvc2.upload.domain.ItemRepository;
import mvc2.upload.domain.UploadFile;
import mvc2.upload.file.FileStore;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemRepository itemRepository;
	private final FileStore fileStore;

	@GetMapping("/items/new")
	public String newItem(@ModelAttribute ItemForm form) {
		return "item-form";
	}

	@PostMapping("/items/new")
	public String saveItem(@ModelAttribute ItemForm form
	                       , RedirectAttributes redirectAttributes) throws IOException {

		UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
		List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

		//데이터베이스 저장
		Item item = new Item();
		item.setItemName(form.getItemName());
		item.setAttachFile(attachFile);
		item.setImageFiles(storeImageFiles);
		itemRepository.save(item);

		redirectAttributes.addAttribute("id", item.getId());
		return "redirect:/items/{id}";
	}

	@GetMapping("/items/{id}")
	public String items(@PathVariable("id") Long id, Model model) {
		Item item = itemRepository.findById(id);
		model.addAttribute("item", item);

		return "item-view";
	}

	@ResponseBody
	@GetMapping("/images/{filename}")
	public Resource downloadImage(@PathVariable("filename") String filename) throws MalformedURLException {
		//file:C:/Users/tanke/Desktop/study/workspaces/file/966aec6b-1aa6-42f9-8790-579e294b69a0.png
		return new UrlResource("file:" + fileStore.getFullPath(filename));
	}

	@GetMapping("/attach/{id}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable("id") Long id) throws MalformedURLException {
		Item item = itemRepository.findById(id);
		String storeFileName = item.getAttachFile().getStoreFileName();
		String uploadFileName = item.getAttachFile().getUploadFileName();
		log.info("storeFileName={}, uploadFileName={}", storeFileName, uploadFileName);

		UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

		String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
		String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.body(resource);
	}
}
