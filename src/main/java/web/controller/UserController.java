package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/"})
    public ModelAndView showUser(@AuthenticationPrincipal User user) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", userService.getUserByName(user.getUsername()));
        return mav;
    }

//    @GetMapping(value = {"/"})
//    public ModelAndView showUserWithoutId(@AuthenticationPrincipal User user) {
//        String targetUrl = "redirect:/" + user.getId().toString();
//        ModelAndView mav = new ModelAndView(targetUrl);
//        mav.addObject("user", userService.getUserByName(user.getUsername()));
//        return mav;
//    }
}
