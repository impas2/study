package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleRepository;
import web.dao.UserRepository;
import web.model.Role;
import web.model.User;
import web.model.UserDTO;
import web.service.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class WebServiceImpl implements WebService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final RoleRepository roleRepository;
    final UserMapper userMapper;

    @Autowired
    public WebServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.findById(id).get());
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
    public UserDTO findUserByIdDTO(Long id) {

        return userRepository.findById(id).map(userMapper::userToDTO).orElseThrow();
    }

    @Override
    public List<User> getAllUsers() {
        return List.copyOf(userRepository.findAll());
    }

    @Override
    public List<UserDTO> getAllUsersDTO() {
        return userRepository.findAll().stream().map(userMapper::userToDTO).collect(Collectors.toList());
    }

    @Override
    public void updateUser(User user) {
        if (!user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(userRepository.findById(user.getId()).get().getPassword());
        }
        userRepository.save(user);

    }

}