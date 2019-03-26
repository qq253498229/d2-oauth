package cn.codeforfun.oauth2server.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

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
}