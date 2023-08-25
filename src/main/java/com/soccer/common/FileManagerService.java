package com.soccer.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		
		@Autowired
		private TextTransService textTransService;
	
	
		public static final String FILE_UPLOAD_PATH = "D:\\choiminseok\\7_final_project\\workspace\\images/";
		
		
		// 이미지 파일 저장 
		public String saveFile(int teamOrUserId, MultipartFile file) {
			
			String directoryName = teamOrUserId + "_" + System.currentTimeMillis() + "/";
			String filePath = FILE_UPLOAD_PATH + directoryName;
			
			File directory = new File(filePath);
			if (directory.mkdir() == false) {
				// 폴더 만드는데 실패 시 이미지 경로 null로 리턴
				return null;
			}
			
			String englishFilename = textTransService.convertToEnglish(file.getOriginalFilename());
			
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(filePath + englishFilename);
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
			return "/images/" + directoryName + englishFilename;
		}
		
		
		// 이미지 파일 삭제 / 폴더 삭제
		public void deleteFile(String profileImagePath) {
			Path path = Paths.get(FILE_UPLOAD_PATH + profileImagePath.replace("/images/", ""));
			
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.info("###[FileManagerService 이미지 삭제 실패] profileImagePath:{}", profileImagePath);
			}
			
			// 디렉토리(폴터) 삭제
			path = path.getParent();
				if (Files.exists(path)) {
					try {
						Files.delete(path);
					} catch (IOException e) {
						logger.info("###[FileManagerService 폴더 삭제 실패] profileImagePath:{}", profileImagePath);
					}
				}
		}

}
		
		














