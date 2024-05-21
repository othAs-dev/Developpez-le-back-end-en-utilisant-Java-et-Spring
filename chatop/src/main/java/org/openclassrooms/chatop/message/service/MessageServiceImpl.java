package org.openclassrooms.chatop.message.service;

import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.message.DTO.MessageDTO;
import org.openclassrooms.chatop.message.entity.MessageEntity;
import org.openclassrooms.chatop.message.repository.MessageRepository;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.rentals.repository.RentalRepository;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;
  private final UserDetailRepository userDetailRepository;
  private final RentalRepository rentalRepository;

  @Override
  public MessageDTO addNewMessage(MessageDTO messageDTO, UUID rentalId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailEntity user = userDetailRepository.findByUsername(authentication.getName());
    RentalEntity rental = rentalRepository.findById(rentalId).orElse(null);

    if (user != null && rental != null) {
      MessageEntity messageEntity = new MessageEntity();
      messageEntity.setMessage(messageDTO.getMessage());
      messageEntity.setOwner(user);
      messageEntity.setRental(rental);

      MessageEntity savedMessage = messageRepository.save(messageEntity);

      MessageDTO savedMessageDTO = new MessageDTO();
      savedMessageDTO.setId(savedMessage.getId());
      savedMessageDTO.setMessage(savedMessage.getMessage());
      savedMessageDTO.setOwnerId(savedMessage.getOwner().getId());
      savedMessageDTO.setRentalId(savedMessage.getRental().getId());

      Map<String, String> response = new HashMap<>();
      response.put("message", "Message added successfully");
    }
    return null;
  }

  @Override
  public List<MessageDTO> getAllMessages() {
    List<MessageEntity> messages = messageRepository.findAll();
    return messages.stream()
      .map(message -> {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setMessage(message.getMessage());
        messageDTO.setOwnerId(message.getOwner().getId());
        messageDTO.setRentalId(message.getRental().getId());
        return messageDTO;
      })
      .collect(Collectors.toList());
  }
}
