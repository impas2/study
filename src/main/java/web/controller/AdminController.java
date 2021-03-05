package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import web.model.User;
import web.service.WebService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    final WebService webService;

    @Autowired
    public AdminController(WebService webService) {
        this.webService = webService;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(User user) {
        ModelAndView mav = new ModelAndView(new RedirectView("/index"));
        webService.save(user);
        return mav;
    }

    @DeleteMapping(value = "/delete")
    public ModelAndView deleteUser(User user) {
        ModelAndView mav = new ModelAndView(new RedirectView("/index"));
        webService.delete(webService.findUserById(user.getId()));
        return mav;
    }

    @PatchMapping(value = "/edit")
    public ModelAndView editUser(User user) {
        ModelAndView mav = new ModelAndView(new RedirectView("/index"));
        webService.updateUser(user);
        return mav;
    }

}
