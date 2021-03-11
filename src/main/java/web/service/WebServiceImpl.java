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
import web.service.mapper.RoleDTOToRoleConverter;
import web.service.mapper.RoleToRoleDTOConverter;
import web.service.mapper.UserDTOToUserConverter;
import web.service.mapper.UserToUserDTOConverter;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Component
@Transactional
public class WebServiceImpl implements WebService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final RoleRepository roleRepository;
    final RoleToRoleDTOConverter roleToRoleDTOConverter;
    final UserToUserDTOConverter userToUserDTOConverter;
    final UserDTOToUserConverter userDTOToUserConverter;

    @Autowired
    public WebServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, RoleToRoleDTOConverter roleToRoleDTOConverter, RoleDTOToRoleConverter roleDTOToRoleConverter, UserToUserDTOConverter userToUserDTOConverter, UserDTOToUserConverter userDTOToUserConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleToRoleDTOConverter = roleToRoleDTOConverter;
        this.userToUserDTOConverter = userToUserDTOConverter;
        this.userDTOToUserConverter = userDTOToUserConverter;
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = userDTOToUserConverter.convert(userDTO);
        save(user);
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
    public List<RoleDTO> getAllRolesDTO() {
        return getAllRoles().stream().map(roleToRoleDTOConverter::convert).collect(Collectors.toList());
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
        return userToUserDTOConverter.convert(findUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        return List.copyOf(userRepository.findAll());
    }

    @Override
    public List<UserDTO> getAllUsersDTO() {
        return getAllUsers().stream().map(userToUserDTOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public void updateUser(User user) {
        if (!user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            //noinspection OptionalGetWithoutIsPresent
            user.setPassword(userRepository.findById(user.getId()).get().getPassword());
        }
        userRepository.save(user);

    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = userDTOToUserConverter.convert(userDTO);
        updateUser(user);
    }

}