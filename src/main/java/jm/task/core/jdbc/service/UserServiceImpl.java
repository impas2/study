package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDaoInterface;

    public UserServiceImpl() {
        userDaoInterface = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        userDaoInterface.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoInterface.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoInterface.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoInterface.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoInterface.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoInterface.cleanUsersTable();
    }
}
