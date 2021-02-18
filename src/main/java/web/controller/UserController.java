package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/{id}"})
    public ModelAndView showUser(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", userService.getUserByName(user.getUsername()));
        return id.equals(user.getId_user()) ? mav : new ModelAndView("errorPage");
    }

    @GetMapping(value = {"/"})
    public ModelAndView showUserWithoutId(@AuthenticationPrincipal User user) {
        String targetUrl = "redirect:/user/" + user.getId_user().toString();
        ModelAndView mav = new ModelAndView(targetUrl);
        mav.addObject("user", userService.getUserByName(user.getUsername()));
        return mav;
    }
}
