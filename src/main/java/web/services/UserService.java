package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.IWebUserDAO;
import web.model.User;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    private final IWebUserDAO userDAO;

    @Autowired
    public UserService(IWebUserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void delUserByID(Long userID) {
        userDAO.delUserByID(userID);
    }

    @Override
    public User getUserByID(Long userID) {
        return userDAO.getUserByID(userID);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void delUser(User user) {
        userDAO.delUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
