package org.openclassrooms.chatop.message.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openclassrooms.chatop.exceptions.ApiException;
import org.openclassrooms.chatop.message.DTO.MessageDTO;
import org.openclassrooms.chatop.message.entity.MessageEntity;
import org.openclassrooms.chatop.message.mapper.MessageMapper;
import org.openclassrooms.chatop.message.repository.MessageRepository;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.rentals.repository.RentalRepository;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;
import org.openclassrooms.chatop.user.repository.UserDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class for managing messages.
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;
  private final UserDetailRepository userDetailRepository;
  private final RentalRepository rentalRepository;

  /**
   * Adds a new message to the database.
   *
   * @param messageDTO The message to add.
   * @return A map containing a message indicating the success of the operation.
   * @throws ApiException.NotFoundException if the user or rental is not found.
   */
  @Override
  public Map<String, String> addNewMessage(MessageDTO messageDTO) {
    UserDetailEntity user = userDetailRepository.findById(messageDTO.getUser_id()).orElse(null);
    RentalEntity rental = rentalRepository.findById(messageDTO.getRental_id()).orElse(null);

    if (user == null) throw new ApiException.NotFoundException("User not found");
    if (rental == null) throw new ApiException.NotFoundException("Rental not found");

    MessageEntity messageEntity = MessageMapper.toEntity(messageDTO, user, rental);
    messageRepository.save(messageEntity);
    log.info("Message saved with success");
    return Map.of("message", "Message send with success");
  }

  /**
   * Retrieves all messages from the database.
   *
   * @return A list of all messages.
   * @throws ApiException.NotFoundException if no messages are found.
   */
  @Override
  public List<MessageDTO> getAllMessages() {
    List<MessageEntity> messages = messageRepository.findAll();
    if (messages.isEmpty()) throw new ApiException.NotFoundException("No messages found");
    log.info("Messages fetched with success");
    return messages.stream()
      .map(MessageMapper::toDTO)
      .collect(Collectors.toList());
  }

}
