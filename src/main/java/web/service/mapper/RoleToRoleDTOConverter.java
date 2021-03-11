package web.service.mapper;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.RoleDTO;

@Component
public class RoleToRoleDTOConverter implements Converter<Role, RoleDTO> {

    @Override
    public RoleDTO convert(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setRoleName(role.getRoleName());
        return roleDTO;
    }
}
