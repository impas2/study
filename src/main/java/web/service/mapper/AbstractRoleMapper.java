package web.service.mapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import web.dao.RoleRepository;
import web.model.Role;
import web.model.RoleDTO;

@Mapper(componentModel = "spring")
public abstract class AbstractRoleMapper {

    RoleRepository roleRepository;

    @Autowired
    public void setMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public abstract RoleDTO fromRole(Role role);

    public Role fromDTO(RoleDTO roleDTO) {
        return roleRepository.findRoleByRoleName(roleDTO.getRoleName());
    };


}
