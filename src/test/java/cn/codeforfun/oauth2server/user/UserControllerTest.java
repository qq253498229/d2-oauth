package cn.codeforfun.oauth2server.user;

import cn.codeforfun.oauth2server.user.exception.UserExistsException;
import cn.codeforfun.oauth2server.user.exception.UsernameOrPasswordEmptyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Resource
    private MockMvc mock;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void newUser_usernameEmpty() throws Exception {
        Mockito.doThrow(UsernameOrPasswordEmptyException.class).when(userService).newUser(any());

        mock.perform(post("/user").contentType(APPLICATION_JSON_UTF8).content(new User().toJson()))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;
    }

    @Test
    public void newUser_passwordEmpty() throws Exception {
        Mockito.doThrow(UsernameOrPasswordEmptyException.class).when(userService).newUser(any());

        mock.perform(post("/user").contentType(APPLICATION_JSON_UTF8).content(new User().toJson()))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;
    }

    @Test
    public void newUser_userExist() throws Exception {
        Mockito.doThrow(UserExistsException.class).when(userService).newUser(any());

        mock.perform(post("/user").contentType(APPLICATION_JSON_UTF8).content(new User().toJson()))
                .andExpect(status().isFound())
                .andDo(print())
        ;
    }

    @Test
    public void newUser_ok() throws Exception {
        mock.perform(post("/user").contentType(APPLICATION_JSON_UTF8).content(new User().toJson()))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }

}