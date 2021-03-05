package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import web.model.Role;
import web.model.User;
import web.service.WebService;

import java.util.Set;


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
        mav.addObject("allRoles", webService.getAllRoles());
        return mav;
    }

    @GetMapping(value = {"/start"})
    public ModelAndView StartPage(Authentication authentication) {
        ModelAndView mav = new ModelAndView("/StartPage");
        mav.addObject("allusers", webService.getAllUsers());
        mav.addObject("allRoles", webService.getAllRoles());
        mav.addObject("loggedUser", webService.findUserByUsername(authentication.getName()));
        return mav;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(User user) {
        ModelAndView mav = new ModelAndView(new RedirectView("/admin/"));
        webService.save(user);
        return mav;
    }

    @DeleteMapping(value = "/delete")
    public ModelAndView deleteUser(User user) {
        ModelAndView mav = new ModelAndView(new RedirectView("/admin/"));
        webService.delete(webService.findUserById(user.getId()));
        return mav;
    }

    @PatchMapping(value = "/edit")
    public ModelAndView editUser(User user) {
        ModelAndView mav = new ModelAndView(new RedirectView("/admin/"));
        webService.updateUser(user);
        return mav;
    }

}
