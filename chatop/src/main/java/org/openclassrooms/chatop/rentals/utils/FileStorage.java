package org.openclassrooms.chatop.rentals.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@Slf4j
public class FileStorage {

  @Value("${server.base-url}")
  private String serverBaseUrl;

  public String imageStorage(MultipartFile picture) throws IOException {
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));
    String uploadDir = "src/main/resources/static/pictures";
    Path uploadPath = Paths.get(uploadDir);
    if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

    try (InputStream inputStream = picture.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

      log.info("Image saved successfully");

      return serverBaseUrl + "/pictures/" + fileName;
    } catch (IOException e) {
      log.error("Failed to save image", e);
      throw new IOException("Failed to save image", e);
    }
  }
}
