package cn.codeforfun.oauth2server.user;

import cn.codeforfun.oauth2server.user.exception.NotUserException;
import cn.codeforfun.oauth2server.user.exception.UserExistsException;
import cn.codeforfun.oauth2server.user.exception.UsernameOrPasswordEmptyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    /**
     * 通过用户名获取用户信息
     */
    @Test
    public void loadUserByUsername() {
        given(userRepository.loadUserByUsername(anyString())).willReturn(new User());
        UserDetails userDetails = userService.loadUserByUsername(anyString());
        assertNotNull(userDetails);
    }

    /**
     * 用户名不存在的情况
     */
    @Test(expected = NotUserException.class)
    public void loadUserByUsername_resultNull() {
        given(userRepository.loadUserByUsername(anyString())).willReturn(null);
        UserDetails userDetails = userService.loadUserByUsername(anyString());
        assertNotNull(userDetails);
    }

    @Test(expected = UsernameOrPasswordEmptyException.class)
    public void newUser_usernameEmpty() {
        User user = new User();
        user.setPassword("123");
        user = userService.newUser(user);
        assertNotNull(user);
    }

    @Test(expected = UsernameOrPasswordEmptyException.class)
    public void newUser_passwordEmpty() {
        User user = new User();
        user.setUsername("123");
        user = userService.newUser(user);
        assertNotNull(user);
    }

    @Test(expected = UserExistsException.class)
    public void newUser_userExists() {
        given(userRepository.loadUserByUsername(any())).willReturn(new User());
        User user = new User();
        user.setUsername("123");
        user.setPassword("123");
        user = userService.newUser(user);
        assertNotNull(user);
    }

    @Test
    public void newUser_ok() {
        given(userRepository.save(any())).willReturn(new User());
        given(roleRepository.findByName(any())).willReturn(new Role());
        User user = new User();
        user.setUsername("123");
        user.setPassword("123");
        user = userService.newUser(user);
        assertNotNull(user);
    }

}