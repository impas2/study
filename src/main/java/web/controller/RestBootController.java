package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import web.model.RoleDTO;
import web.model.User;
import web.model.UserDTO;
import web.service.WebService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RestBootController {

    private final WebService webService;

    @Autowired
    public RestBootController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(webService.getAllUsersDTO(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(webService.findUserByIdDTO(id), HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<UserDTO> setNewUser(@RequestBody UserDTO user) {
        webService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping(value = "/user/{id}", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        webService.updateUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delUser(@PathVariable("id") Long id) {
        webService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/role")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return new ResponseEntity<>(webService.getAllRolesDTO(), HttpStatus.OK);
    }

    @GetMapping(value = "/userinfo")
    public ResponseEntity<UserDTO> getCurrentUserInfo(Authentication authentication) {
        UserDTO userDTO = webService.findUserByUsernameDTO(authentication.getName());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

}
