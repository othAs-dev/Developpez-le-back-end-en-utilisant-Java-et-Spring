package org.openclassrooms.chatop.message.mapper;

import org.openclassrooms.chatop.message.DTO.MessageDTO;
import org.openclassrooms.chatop.message.entity.MessageEntity;
import org.openclassrooms.chatop.rentals.entity.RentalEntity;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;

public class MessageMapper {

  public static MessageDTO toDTO(MessageEntity messageEntity) {
    if (messageEntity == null) {
      return null;
    }
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setId(messageEntity.getId());
    messageDTO.setMessage(messageEntity.getMessage());
    messageDTO.setOwnerId(messageEntity.getOwner().getId());
    messageDTO.setRentalId(messageEntity.getRental().getId());
    return messageDTO;
  }

  public static MessageEntity toEntity(MessageDTO messageDTO, UserDetailEntity owner, RentalEntity rental) {
    if (messageDTO == null) {
      return null;
    }
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setId(messageDTO.getId());
    messageEntity.setMessage(messageDTO.getMessage());
    messageEntity.setOwner(owner);
    messageEntity.setRental(rental);
    return messageEntity;
  }
}
