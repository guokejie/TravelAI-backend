package org.tech.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tech.common.constants.JwtConstants;
import org.tech.common.result.Result;
import org.tech.service.AuthService;
import org.tech.vo.UserVO;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    /**
     * 获取当前用户信息
     *
     * @param userId 用户ID（从请求属性中获取）
     * @return 用户信息
     */
    @GetMapping("/info")
    public Result<UserVO> getUserInfo(@RequestAttribute(value = JwtConstants.CLAIM_USER_ID, required = false) Long userId) {
        if (userId == null) {
            return Result.error(401, "未登录");
        }
        UserVO userVO = authService.getUserInfo(userId);
        return Result.success(userVO);
    }
}
