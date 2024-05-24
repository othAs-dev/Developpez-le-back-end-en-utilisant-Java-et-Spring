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
import java.util.UUID;

@Slf4j
@Service
public class FileStorage {

  @Value("${server.base-url}")
  private String serverBaseUrl;

  /**
   * Saves the image to the server and returns the path to the saved image.
   *
   * @param picture the image to save
   * @return the path to the saved image
   * @throws IOException if an error occurs while saving the image
   */
  public String imageStorage(MultipartFile picture) throws IOException {
    String uuid = UUID.randomUUID().toString();

    String fileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));
    String generatedFileName = uuid + "_" + fileName;

    Path uploadPath = Paths.get("src/main/resources/static/pictures");
    if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

    try (InputStream inputStream = picture.getInputStream()) {
      Path filePath = uploadPath.resolve(generatedFileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

      log.info("Image saved successfully");

      return serverBaseUrl + "/pictures/" + generatedFileName;
    } catch (IOException e) {
      log.error("Failed to save image", e);
      throw new IOException("Failed to save image", e);
    }
  }
}
