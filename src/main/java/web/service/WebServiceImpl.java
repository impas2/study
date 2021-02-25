package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleRepository;
import web.dao.UserRepository;
import web.model.Role;
import web.model.User;

import java.util.List;

@Component
@Transactional
public class WebServiceImpl implements WebService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Autowired
    public WebServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        String encryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getAllUsers() {
        return List.copyOf(userRepository.findAll());
    }

}