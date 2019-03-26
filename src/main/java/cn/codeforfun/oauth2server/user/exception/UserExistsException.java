package cn.codeforfun.oauth2server.user.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FOUND;

/**
 * 用户名已存在
 *
 * @author wangbin
 */
@ResponseStatus(FOUND)
public class UserExistsException extends RuntimeException {
}
