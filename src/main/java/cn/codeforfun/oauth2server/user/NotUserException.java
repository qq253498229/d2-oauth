package cn.codeforfun.oauth2server.user;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * 用户名不存在异常
 * Email 253498229@qq.com
 *
 * @author wangbin
 */
@ResponseStatus(NOT_FOUND)
class NotUserException extends RuntimeException {
}
