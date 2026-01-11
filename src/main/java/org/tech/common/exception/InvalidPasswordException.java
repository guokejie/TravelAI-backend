package org.tech.common.exception;

/**
 * 密码错误异常
 */
public class InvalidPasswordException extends BusinessException {

    public InvalidPasswordException() {
        super(400, "密码错误");
    }
}
