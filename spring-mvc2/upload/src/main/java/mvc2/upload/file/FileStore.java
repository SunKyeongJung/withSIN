package mvc2.upload.file;

import mvc2.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

	@Value("${file.dir}")
	private String fileDir;

	/**
	 * 파일명을 넣은 fullPath 세팅
	 * @param filename
	 * @return
	 */
	public String getFullPath(String filename) {
		return fileDir + filename;
	}

	public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
		List<UploadFile> storeFileResult = new ArrayList<>();

		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()) {
				storeFileResult.add(storeFile(multipartFile));
			}
		}

		return storeFileResult;
	}

	/**
	 * 파일 저장
	 * @param multipartFile
	 * @return UploadFile
	 * @throws IOException
	 */
	public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile.isEmpty()) {
			return null;
		}

		//image.png -> qwe-qwe-123-qwe-qwer.png
		String originalFilename = multipartFile.getOriginalFilename();
		String storeFileName = createStoreFileName(originalFilename);
		multipartFile.transferTo(new File(getFullPath(storeFileName)));

		return new UploadFile(originalFilename, storeFileName);
	}

	/**
	 * 저장할 파일명 생성 (UUID.확장자)
	 * @param originalFilename
	 * @return
	 */
	private String createStoreFileName(String originalFilename) {
		String ext = extractExt(originalFilename);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}

	/**
	 * filename에서 확장자 추출
	 * @param originalFilename
	 * @return
	 */
	private String extractExt(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		return originalFilename.substring(pos + 1);
	}
}
