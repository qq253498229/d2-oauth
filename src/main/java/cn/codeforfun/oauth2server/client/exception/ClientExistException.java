package cn.codeforfun.oauth2server.client.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * @author wangbin
 */
@ResponseStatus(CONFLICT)
public class ClientExistException extends RuntimeException {
}
