package com.auzzythebear.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
  public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile)
      throws IOException {
    Path uploadPath = Paths.get(uploadDir);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    try (InputStream inputStream = multipartFile.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
      throw new IOException("Could not save image file: " + fileName, ioe);
    }
  }

  public static void cleanDir(String dir) {
    Path dirPath = Paths.get(dir);

    try {
      Files.list(dirPath)
          .forEach(
              file -> {
                if (!Files.isDirectory(file)) {
                  try {
                    Files.delete(file);
                  } catch (IOException ex) {
                    System.out.println("Could not delete file: " + file);
                  }
                }
              });
    } catch (IOException ex) {
      System.out.println("Could not list directory: " + dirPath);
    }
  }
}
