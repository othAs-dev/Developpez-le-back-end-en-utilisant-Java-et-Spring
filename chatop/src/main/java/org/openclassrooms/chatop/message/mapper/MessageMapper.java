package org.openclassrooms.chatop.message.mapper;

import org.openclassrooms.chatop.message.DTO.MessageDTO;
import org.openclassrooms.chatop.message.entity.MessageEntity;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;

/**
 * Mapper class for converting MessageEntity objects to MessageDTO objects.
 */
public class MessageMapper {

  /**
   * Converts a MessageEntity object to a MessageDTO object.
   * @param messageEntity The MessageEntity object to convert.
   * @return The converted MessageDTO object.
   */
  public static MessageDTO toDTO(MessageEntity messageEntity) {
    if (messageEntity == null) {
      return null;
    }
    return MessageDTO.builder()
      .id(messageEntity.getId())
      .message(messageEntity.getMessage())
      .user_id(messageEntity.getUser_id().getId())
      .rental_id(messageEntity.getRental().getId())
      .build();
  }

  /**
   * Converts a MessageDTO object to a MessageEntity object.
   * @param messageDTO The MessageDTO object to convert.
   * @param owner The UserDetailEntity object that owns the message.
   * @param rental The RentalEntity object that the message is associated with.
   * @return The converted MessageEntity object.
   */
  public static MessageEntity toEntity(MessageDTO messageDTO, UserDetailEntity owner, RentalEntity rental) {
    if (messageDTO == null) {
      return null;
    }
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setId(messageDTO.getId());
    messageEntity.setMessage(messageDTO.getMessage());
    messageEntity.setUser_id(owner);
    messageEntity.setRental(rental);
    return messageEntity;
  }
}
