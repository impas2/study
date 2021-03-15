package web.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import web.model.Role;
import web.model.User;
import web.model.UserDTO;

@Mapper(componentModel = "spring", uses = { Role.class, AbstractRoleMapper.class })
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    public UserDTO fromUser(User user);

    @InheritInverseConfiguration
    public User fromDTO(UserDTO userDTO);
}
