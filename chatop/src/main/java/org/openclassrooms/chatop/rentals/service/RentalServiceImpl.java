package org.openclassrooms.chatop.rentals.service;

import org.openclassrooms.chatop.rentals.DTO.RentalDTO;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.rentals.mapper.RentalMapper;
import org.openclassrooms.chatop.rentals.repository.RentalRepository;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {
  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private UserDetailRepository userDetailRepository;

  @Value("${server.base-url}")
  private String serverBaseUrl;


  @Override
  public Map<String, String> addNewRental(String name, int surface, Double price, String description, MultipartFile picture) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailEntity user = userDetailRepository.findByEmail(authentication.getName());

    try {
      String picturePath = savePicture(picture); // Méthode pour sauvegarder l'image et obtenir son chemin
      RentalEntity rentalEntity = RentalEntity.builder()
        .name(name)
        .surface(surface)
        .price(price)
        .description(description)
        .picture(picturePath)
        .owner(user)
        .build();
      rentalRepository.save(rentalEntity);

      return Map.of("message", "Rental created !");
    } catch (IOException e) {
      e.printStackTrace();
      return Map.of("error", "Failed to save picture.");
    }
  }

  private String savePicture(MultipartFile picture) throws IOException {
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));
    String uploadDir = "src/main/resources/static/pictures"; // Chemin de stockage des images
    Path uploadPath = Paths.get(uploadDir);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    try (InputStream inputStream = picture.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
      return serverBaseUrl + "/pictures/" + fileName;
    }
  }

  @Override
  public Map<String, String> updateRental(RentalDTO rentalDTO, Long id) {
    RentalEntity rental = rentalRepository.findById(id).orElse(null);
    if (rental != null) {
      rental.setName(rentalDTO.getName());
      rental.setDescription(rentalDTO.getDescription());
      rental.setPrice(rentalDTO.getPrice());
      rental.setSurface(rentalDTO.getSurface());
      rentalRepository.save(rental);

      return Map.of("message", "Rental updated !");
    }
    return null;
  }

  @Override
  public RentalDTO getRentalById(Long id) {
    RentalEntity rental = rentalRepository.findById(id).orElse(null);
    return RentalMapper.toDTO(rental);
  }

  @Override
  public List<RentalDTO> getRentals() {
    List<RentalEntity> rentals = rentalRepository.findAll();
    return rentals.stream()
      .map(RentalMapper::toDTO)
      .collect(Collectors.toList());
  }
}
