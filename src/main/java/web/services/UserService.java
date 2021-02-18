package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.IWebUserDAO;
import web.model.User;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    private final IWebUserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IWebUserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        String currPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(currPassword));
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
    public void updateUser(User user, Long userID) {
        String currPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(currPassword));
        userDAO.updateUser(user, userID);
    }

    @Override
    public void delUser(User user) {
        userDAO.delUser(user);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
