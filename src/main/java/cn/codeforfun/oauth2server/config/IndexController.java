package cn.codeforfun.oauth2server.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wangbin
 */
@RestController
public class IndexController {
    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }
}
