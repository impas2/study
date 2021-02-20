package web.services;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void addUser(User user);
    void delUserByID(Long userID);
    User getUserByID(Long userID);
    void updateUser(User user);
    void updateUserWithPassword(User user, String newPassword);
    void delUser(User user);
    User getUserByName(String name);
    List<Role> getAllRoles();
    Role getRoleById(Long roleID);
}
