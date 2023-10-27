package com.fdmgroup.GameBlog.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class BlogPostPictureUtil {
	public static void savePicture(String uploadDir, String pictureName, MultipartFile multipartFile) throws IOException{
		Path uploadPath = Paths.get(uploadDir);
		//check if image folder exists, if not create one
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		 //check if image exists, if not create one, if yes replace it
		Path filePath = uploadPath.resolve(pictureName);
		if (Files.exists(filePath)) {
			Files.delete(filePath);
		}
		 //copy the images into the folder
		try (InputStream inputStream = multipartFile.getInputStream()){
			Files.copy(inputStream, filePath);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
