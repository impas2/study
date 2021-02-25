package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping(value = "/")
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
    public String addUser(@ModelAttribute("blankUser") User user) {
        webService.save(user);
        return "redirect:/admin/";
    }

    @DeleteMapping(value = "/delete")
    public String deleteUser(@ModelAttribute("id") Long id) {
        webService.delete(webService.findUserById(id));
        return "redirect:/admin/";
    }

}
