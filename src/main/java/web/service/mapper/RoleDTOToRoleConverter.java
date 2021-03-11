package web.service.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import web.dao.RoleRepository;
import web.model.Role;
import web.model.RoleDTO;

@Component
public class RoleDTOToRoleConverter implements Converter<RoleDTO, Role> {

    RoleRepository roleRepository;

    RoleDTOToRoleConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role convert(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        Role role = new Role();

        role.setRoleName(roleDTO.getRoleName());
        role.setId(roleRepository.findRoleByRoleName(roleDTO.getRoleName()).getId());
        return role;
    }
}
