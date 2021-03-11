package web.service.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.RoleDTO;
import web.model.User;
import web.model.UserDTO;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDTOToUserConverter implements Converter<UserDTO, User> {

    RoleDTOToRoleConverter dtoToRole;

    public UserDTOToUserConverter(RoleDTOToRoleConverter dtoToRole) {
        this.dtoToRole = dtoToRole;
    }

    @Override
    public User convert(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setRoles( roleDTOSetToRoleSet( userDTO.getRoles() ) );
        user.setPassword( userDTO.getPassword() );
        user.setAge( userDTO.getAge() );
        user.setFirstname( userDTO.getFirstname() );
        user.setLastname( userDTO.getLastname() );

        return user;
    }

    protected Set<Role> roleDTOSetToRoleSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDTO roleDTO : set ) {
            set1.add(dtoToRole.convert(roleDTO) );
        }

        return set1;
    }
}
