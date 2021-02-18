package web.dao;

import web.model.User;

import java.util.List;

public interface IWebUserDAO {

    void addUser(User user);
    List<User> getAllUsers();
    void delUserByID(Long userID);
    void updateUser(User user, Long userID);
    User getUserByID(Long userID);
    void delUser(User user);
    User getUserByName(String username);
}
