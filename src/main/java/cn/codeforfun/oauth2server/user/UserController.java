package cn.codeforfun.oauth2server.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity newUser(@RequestBody User user) {
        userService.newUser(user);
        return ok().build();
    }
}
