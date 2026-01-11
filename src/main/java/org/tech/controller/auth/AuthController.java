package org.tech.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tech.common.result.Result;
import org.tech.dto.LoginRequest;
import org.tech.dto.RegisterRequest;
import org.tech.service.AuthService;
import org.tech.vo.LoginVO;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 用户注册
     *
     * @param request 注册请求
     * @return 登录响应信息
     */
    @PostMapping("/register")
    public Result<LoginVO> register(@Valid @RequestBody RegisterRequest request) {
        LoginVO loginVO = authService.register(request);
        return Result.success("注册成功", loginVO);
    }

    /**
     * 用户登录
     *
     * @param request 登录请求
     * @return 登录响应信息
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginRequest request) {
        LoginVO loginVO = authService.login(request);
        return Result.success("登录成功", loginVO);
    }

    /**
     * 用户退出登录
     *
     * @return 成功消息
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        // 由于使用 JWT 无状态认证，前端删除 Token 即可
        // 后端可以实现 Token 黑名单（如果需要）
        return Result.success("退出成功");
    }
}
