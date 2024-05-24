package org.openclassrooms.chatop.security.mapper;

import org.openclassrooms.chatop.security.DTO.UserDetailDTO;
import org.openclassrooms.chatop.user.entity.UserDetailEntity;

/**
 * Mapper class for converting UserDetailEntity objects to UserDetailDTO objects.
 */
public class UserDetailMapper {

  /**
   * Converts a UserDetailEntity object to a UserDetailDTO object.
   * @param userEntity The UserDetailEntity object to convert.
   * @return The converted UserDetailDTO object.
   */
  public static UserDetailDTO toUserDetailDTO(UserDetailEntity userEntity) {
    UserDetailDTO userDTO = new UserDetailDTO();
    userDTO.setId(userEntity.getId());
    userDTO.setName(userEntity.getUsername());
    userDTO.setEmail(userEntity.getEmail());
    userDTO.setCreated_at(userEntity.getCreated_at());
    userDTO.setUpdated_at(userEntity.getUpdated_at());
    return userDTO;
  }
}
