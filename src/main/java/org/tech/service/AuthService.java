package org.tech.service;

import org.tech.dto.LoginRequest;
import org.tech.dto.RegisterRequest;
import org.tech.vo.LoginVO;
import org.tech.vo.UserVO;

/**
 * 认证服务接口
 */
public interface AuthService {

    /**
     * 用户注册
     *
     * @param request 注册请求
     * @return 登录响应信息
     */
    LoginVO register(RegisterRequest request);

    /**
     * 用户登录
     *
     * @param request 登录请求
     * @return 登录响应信息
     */
    LoginVO login(LoginRequest request);

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserVO getUserInfo(Long userId);
}
