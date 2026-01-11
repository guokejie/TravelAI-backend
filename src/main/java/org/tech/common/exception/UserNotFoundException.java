package org.tech.common.exception;

/**
 * 用户不存在异常
 */
public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String message) {
        super(404, message);
    }

    public UserNotFoundException() {
        super(404, "用户不存在");
    }
}
