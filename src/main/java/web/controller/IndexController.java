package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.service.WebService;

@Controller
@RequestMapping(value = {"", "/"})
public class IndexController {

    final WebService webService;

    @Autowired
    public IndexController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping(value = {"index", "",})
    public ModelAndView StartPage(Authentication authentication) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("allUsers", webService.getAllUsers());
        mav.addObject("allRoles", webService.getAllRoles());
        mav.addObject("loggedUser", webService.findUserByUsername(authentication.getName()));
        return mav;
    }

}
