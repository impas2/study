package web.service;

import web.model.Role;
import web.model.RoleDTO;
import web.model.User;
import web.model.UserDTO;

import java.util.List;

public interface WebService {
    void saveUser(User user);

    void saveUser(UserDTO userDTO);

    void deleteUser(Long id);

    List<Role> getAllRoles();

    List<RoleDTO> getAllRolesDTO();

    User findUserByUsername(String username);

    User findUserById(Long id);

    UserDTO findUserByIdDTO(Long id);

    UserDTO findUserByUsernameDTO(String username);

    List<User> getAllUsers();

    List<UserDTO> getAllUsersDTO();

    void updateUser(User user);

    void updateUser(UserDTO userDTO);
}
