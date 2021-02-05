package web.services;

import web.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    void addUser(User user);
    void delUserByID(Long userID);
    User getUserByID(Long userID);
    void updateUser(User user);
    void delUser(User user);
}
