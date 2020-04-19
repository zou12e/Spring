package com.sc.producer.dto;


import com.sc.producer.model.User;
import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface EntityCopyMapper {
    EntityCopyMapper INSTANCE = Mappers.getMapper(EntityCopyMapper.class);
    User toUser(UserVO vo);
    UserDTO toUserDTO(User user);
    List<UserDTO> toListUserDTO(List<User> list);
}
