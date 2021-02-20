package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface WebUserDAO {

    void addUser(User user);
    List<User> getAllUsers();
    void delUserByID(Long userID);
    void updateUser(User user);
    User getUserByID(Long userID);
    void delUser(User user);
    User getUserByName(String username);
    List<Role> getAllRoles();
    Role getRoleById(Long roleID);
}
