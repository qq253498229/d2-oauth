package cn.codeforfun.oauth2server.user;

import cn.codeforfun.oauth2server.user.exception.NotUserException;
import cn.codeforfun.oauth2server.user.exception.UserExistsException;
import cn.codeforfun.oauth2server.user.exception.UsernameOrPasswordEmptyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author wangbin
 */
@Component
public class UserServiceImpl implements UserDetailsService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PasswordEncoder encoder;

    /**
     * 通过用户名获取用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.loadUserByUsername(username);
        if (userDetails == null) {
            throw new NotUserException();
        }
        return userDetails;
    }

    User newUser(User user) {
        if (ObjectUtils.isEmpty(user.getUsername()) || ObjectUtils.isEmpty(user.getPassword())) {
            throw new UsernameOrPasswordEmptyException();
        }
        UserDetails userDetails = userRepository.loadUserByUsername(user.getUsername());
        if (!ObjectUtils.isEmpty(userDetails)) {
            throw new UserExistsException();
        }
        Role role = roleRepository.findByName("USER");
        user.setRoles(Collections.singletonList(role));
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
