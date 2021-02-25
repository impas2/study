package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface WebService {
    void save(User user);

    void delete(User user);

    List<Role> getAllRoles();

    User findUserByUsername(String username);

    User findUserById(Long id);

    List<User> getAllUsers();
}
