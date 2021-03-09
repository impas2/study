package web.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import web.model.Role;
import web.model.RoleDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToDTO(Role role);

    @InheritInverseConfiguration
    Role userFromDTO(RoleDTO roleDTO);

}