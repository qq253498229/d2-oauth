package cn.codeforfun.oauth2server.user.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * 用户名不存在异常
 * Email 253498229@qq.com
 *
 * @author wangbin
 */
@ResponseStatus(NOT_FOUND)
public class NotUserException extends RuntimeException {
}
