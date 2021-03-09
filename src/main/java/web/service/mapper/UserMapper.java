package web.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import web.model.RoleDTO;
import web.model.User;
import web.model.UserDTO;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDTO userToDTO(User user);

    @InheritInverseConfiguration
    User userFromDTO(UserDTO userDTO);

}


