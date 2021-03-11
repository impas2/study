package web.service;

import web.model.Role;
import web.model.RoleDTO;
import web.model.User;
import web.model.UserDTO;

import java.util.List;

public interface WebService {
    void save(User user);

    void save(UserDTO userDTO);

    void delete(User user);

    void delete(Long id);

    List<Role> getAllRoles();

    List<RoleDTO> getAllRolesDTO();

    User findUserByUsername(String username);

    User findUserById(Long id);

    UserDTO findUserByIdDTO(Long id);

    List<User> getAllUsers();

    List<UserDTO> getAllUsersDTO();

    void updateUser(User user);

    void updateUser(UserDTO userDTO);
}
