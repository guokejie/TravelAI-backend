package org.tech.common.exception;

/**
 * Token 无效异常
 */
public class TokenInvalidException extends BusinessException {

    public TokenInvalidException() {
        super(401, "Token无效或已过期");
    }
}
