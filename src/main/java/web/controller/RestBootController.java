package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(value="/user")
    public List<UserDTO> getAllUsers() {
        return webService.getAllUsersDTO();
    }

    @GetMapping(value="/user/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        return webService.findUserByIdDTO(id);
    }

    @PostMapping(value = "/user")
    public void setNewUser(@RequestBody UserDTO user) {
        webService.save(user);
    }

    @PatchMapping(value="/user/{id}", consumes = { MediaType.ALL_VALUE })
    public void updateUser(@RequestBody UserDTO userDTO) {
        webService.updateUser(userDTO);
    }

    @DeleteMapping("/user/{id}")
    public void delUser(@PathVariable("id") Long id) {
        webService.delete(id);
    }

    @GetMapping(value = "/role")
    public List<RoleDTO> getAllRoles() {
        return webService.getAllRolesDTO();
    }

}
