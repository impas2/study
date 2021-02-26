package web.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @GetMapping(value = {"/", ""})
    public ModelAndView getUser(@AuthenticationPrincipal User user) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", user);
        return mav;
    }
}
