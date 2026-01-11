package org.tech.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.tech.common.exception.BusinessException;
import org.tech.common.exception.InvalidPasswordException;
import org.tech.common.exception.UserAlreadyExistsException;
import org.tech.common.exception.UserNotFoundException;
import org.tech.dto.LoginRequest;
import org.tech.dto.RegisterRequest;
import org.tech.entity.User;
import org.tech.service.AuthService;
import org.tech.service.UserService;
import org.tech.util.JwtUtil;
import org.tech.vo.LoginVO;
import org.tech.vo.UserVO;

import java.time.LocalDateTime;

/**
 * 认证服务实现类
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public LoginVO register(RegisterRequest request) {
        // 校验用户名是否存在
        if (userService.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("用户名已存在");
        }

        // 校验手机号是否存在
        if (userService.existsByPhone(request.getPhone())) {
            throw new UserAlreadyExistsException("手机号已被注册");
        }

        // 创建用户
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());
        user.setNickname(request.getNickname() != null ? request.getNickname() : request.getUsername());
        // 使用 BCrypt 加密密码
        user.setPassword(BCrypt.withDefaults().hashToString(6, request.getPassword().toCharArray()));
        user.setStatus(1);

        // 保存用户
        boolean saved = userService.save(user);
        if (!saved) {
            throw new BusinessException("注册失败");
        }

        // 生成 Token
        String token = JwtUtil.generateToken(user.getId(), user.getUsername(), user.getPhone());

        // 构建返回结果
        LoginVO loginVO = new LoginVO();
        loginVO.setUserId(user.getId());
        loginVO.setUsername(user.getUsername());
        loginVO.setPhone(user.getPhone());
        loginVO.setToken(token);
        // 计算 Token 过期时间（7天后）
        loginVO.setExpireTime(LocalDateTime.now().plusSeconds(604800));

        return loginVO;
    }

    @Override
    public LoginVO login(LoginRequest request) {
        // 根据手机号查询用户
        User user = userService.getByPhone(request.getPhone());
        if (user == null) {
            throw new UserNotFoundException("用户不存在");
        }

        // 检查用户状态
        if (user.getStatus() == 0) {
            throw new BusinessException("账号已被禁用");
        }

        // 验证密码
        boolean passwordMatch = BCrypt.verifyer().verify(request.getPassword().toCharArray(), user.getPassword()).verified;
        if (!passwordMatch) {
            throw new InvalidPasswordException();
        }

        // 生成 Token
        String token = JwtUtil.generateToken(user.getId(), user.getUsername(), user.getPhone());

        // 构建返回结果
        LoginVO loginVO = new LoginVO();
        loginVO.setUserId(user.getId());
        loginVO.setUsername(user.getUsername());
        loginVO.setPhone(user.getPhone());
        loginVO.setToken(token);
        // 计算 Token 过期时间（7天后）
        loginVO.setExpireTime(LocalDateTime.now().plusSeconds(604800));

        return loginVO;
    }

    @Override
    public UserVO getUserInfo(Long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setUserId(user.getId());

        return userVO;
    }
}
