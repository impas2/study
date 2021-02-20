package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = { "/users"} )
    public String listUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users-list";
    }

    @GetMapping(value ="/add")
    public ModelAndView addUserForm(Model model) {
        User user = new User();
        ModelAndView mav = new ModelAndView("add-user");
        mav.addObject("user", user);
        List<Role> roles = userService.getAllRoles();
        mav.addObject("allRoles", roles);
        return mav;
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
        model.addAttribute("allRoles", userService.getAllRoles());
        return "edit";
    }

    @GetMapping(value = {"/users/{id}", "/"})
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserByID(id));
        return "user";
    }

    @PatchMapping(value = "/users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("newPassword") String newPassword) {
        if (newPassword.equals("")) {
            userService.updateUser(user);
            System.out.println("Not New Pass");
        } else {
            userService.updateUserWithPassword(user, newPassword);
            System.out.println("New Pass" + newPassword);
        }
        return "redirect:/admin/users";
    }

}
