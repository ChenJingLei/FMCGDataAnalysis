package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cjl20 on 2016/7/5.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMenu() {
        return "views/menu";
    }

    @RequestMapping(value = "/partials/main", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("welcome","欢迎您登陆本系统");
        return "views/partials/main";
    }
}
