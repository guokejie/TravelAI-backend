package org.tech.common.exception;

/**
 * 用户已存在异常
 */
public class UserAlreadyExistsException extends BusinessException {

    public UserAlreadyExistsException(String message) {
        super(400, message);
    }

    public UserAlreadyExistsException() {
        super(400, "用户已存在");
    }
}
