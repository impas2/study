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
public class UserToUserDTOConverter implements Converter<User, UserDTO> {

    final RoleToRoleDTOConverter roleToDTO;

    public UserToUserDTOConverter(RoleToRoleDTOConverter roleToDTO) {
        this.roleToDTO = roleToDTO;
    }

    @Override
    public UserDTO convert(User user) {

        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setFirstname( user.getFirstname() );
        userDTO.setLastname( user.getLastname() );
        userDTO.setAge( user.getAge() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setRoles( roleSetToRoleDTOSet( user.getRoles() ) );

        return userDTO;
    }

    protected Set<RoleDTO> roleSetToRoleDTOSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set1 = new HashSet<>(Math.max((int) (set.size() / .75f) + 1, 16));
        for ( Role role : set ) {
            set1.add(roleToDTO.convert(role));
        }

        return set1;
    }

}
