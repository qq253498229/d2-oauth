package cn.codeforfun.oauth2server.user.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 用户名或密码为空
 *
 * @author wangbin
 */
@ResponseStatus(BAD_REQUEST)
public class UsernameOrPasswordEmptyException extends RuntimeException {
}
