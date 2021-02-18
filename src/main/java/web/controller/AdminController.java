package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.services.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final IUserService userService;

    @Autowired
    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = { "/users"} )
    public String listUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users-list";
    }

    @GetMapping(value ="/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/{id}/remove")
    public String deleteUserApproving(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserByID(id));
        return "remove";
    }

    @DeleteMapping(value = "/users/*")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.delUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/{id}/edit")
    public String updateUserApproving(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserByID(id));
        return "edit";
    }

    @GetMapping(value = {"/users/{id}", "/"})
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserByID(id));
        return "user";
    }

    @PatchMapping(value = "/users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/admin/users";
    }

}
