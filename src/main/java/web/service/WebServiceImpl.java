package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleRepository;
import web.dao.UserRepository;
import web.model.Role;
import web.model.RoleDTO;
import web.model.User;
import web.model.UserDTO;
import web.service.mapper.*;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Component
@Transactional
public class WebServiceImpl implements WebService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final RoleRepository roleRepository;
    final UserMapper userMapper;
    final AbstractRoleMapper roleMapper;


    @Autowired
    public WebServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, AbstractRoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = userMapper.fromDTO(userDTO);
        saveUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findById(id).get());
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<RoleDTO> getAllRolesDTO() {
        return getAllRoles().stream().map(roleMapper::fromRole).collect(Collectors.toList());
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
        return userMapper.fromUser(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserDTO findUserByUsernameDTO(String username) {
        return userMapper.fromUser(findUserByUsername(username));
    }

    @Override
    public List<User> getAllUsers() {
        return List.copyOf(userRepository.findAll());
    }

    @Override
    public List<UserDTO> getAllUsersDTO() {
        return getAllUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
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

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = userMapper.fromDTO(userDTO);
        updateUser(user);
    }

}