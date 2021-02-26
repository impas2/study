package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import web.model.User;
import web.service.WebService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    WebService webService;

    @Autowired
    public AdminController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping(value = {"/", ""})
    public ModelAndView adminPage() {
        ModelAndView mav = new ModelAndView("/admin");
        mav.addObject("allusers", webService.getAllUsers());
        return mav;
    }

    @GetMapping(value = "/add")
    public ModelAndView getAddUserForm() {
        ModelAndView mav = new ModelAndView("/add");
        mav.addObject("blankUser", new User());
        mav.addObject("allRoles", webService.getAllRoles());
        return mav;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@ModelAttribute("blankUser") User user) {
        ModelAndView mav = new ModelAndView(new RedirectView("/admin/"));
        webService.save(user);
        return mav;
    }

    @DeleteMapping(value = "/delete")
    public ModelAndView deleteUser(@ModelAttribute("username") String username) {
        ModelAndView mav = new ModelAndView(new RedirectView("/admin/"));
        webService.delete(webService.findUserByUsername(username));
        return mav;
    }

    @GetMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute("username") String username) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("user", webService.findUserByUsername(username));
        mav.addObject("allRoles", webService.getAllRoles());
        return mav;
    }

    @PatchMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView(new RedirectView("/admin/"));
        webService.updateUser(user);
        return mav;
    }

}
