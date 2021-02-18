package web.services;

import web.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    void addUser(User user);
    void delUserByID(Long userID);
    User getUserByID(Long userID);
    void updateUser(User user, Long userID);
    void delUser(User user);
    User getUserByName(String name);
}
