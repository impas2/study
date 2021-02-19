package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.services.IUserService;

import java.util.List;
import java.util.Set;

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
    public ModelAndView updateUserApproving(@PathVariable("id") Long id) {
        User user = userService.getUserByID(id);
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("user", user);
        List<Role> roles = userService.getAllRoles();
        mav.addObject("allRoles", roles);
        return mav;
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
