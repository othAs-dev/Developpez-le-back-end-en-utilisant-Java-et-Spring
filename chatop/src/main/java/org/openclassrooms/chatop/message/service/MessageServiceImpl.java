package org.openclassrooms.chatop.message.service;

import lombok.AllArgsConstructor;
import org.openclassrooms.chatop.exceptions.ApiException;
import org.openclassrooms.chatop.message.DTO.MessageDTO;
import org.openclassrooms.chatop.message.entity.MessageEntity;
import org.openclassrooms.chatop.message.mapper.MessageMapper;
import org.openclassrooms.chatop.message.repository.MessageRepository;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.rentals.repository.RentalRepository;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    if (user == null) throw new ApiException.NotFoundException("User not found");
    if (rental == null) throw new ApiException.NotFoundException("Rental not found");

    MessageEntity messageEntity = MessageMapper.toEntity(messageDTO, user, rental);
    MessageEntity savedMessage = messageRepository.save(messageEntity);
    return MessageMapper.toDTO(savedMessage);
  }

  @Override
  public List<MessageDTO> getAllMessages() {
    List<MessageEntity> messages = messageRepository.findAll();
    if (messages.isEmpty()) throw new ApiException.NotFoundException("No messages found");
    return messages.stream()
      .map(MessageMapper::toDTO)
      .collect(Collectors.toList());
  }

}
