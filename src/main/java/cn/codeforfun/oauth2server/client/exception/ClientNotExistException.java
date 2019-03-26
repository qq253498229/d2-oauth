package cn.codeforfun.oauth2server.client.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author wangbin
 */
@ResponseStatus(NOT_FOUND)
public class ClientNotExistException extends RuntimeException {
}
