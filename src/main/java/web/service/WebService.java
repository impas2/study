package web.service;

import web.model.Role;
import web.model.User;
import web.model.UserDTO;

import java.util.List;

public interface WebService {
    void save(User user);

    void delete(User user);

    void delete(Long id);

    List<Role> getAllRoles();

    User findUserByUsername(String username);

    User findUserById(Long id);

    UserDTO findUserByIdDTO(Long id);

    List<User> getAllUsers();

    List<UserDTO> getAllUsersDTO();

    void updateUser(User user);

}
