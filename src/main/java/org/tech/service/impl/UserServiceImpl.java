package org.tech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tech.entity.User;
import org.tech.mapper.UserMapper;
import org.tech.service.UserService;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getByUsername(String username) {
        return lambdaQuery()
                .eq(User::getUsername, username)
                .one();
    }

    @Override
    public User getByPhone(String phone) {
        return lambdaQuery()
                .eq(User::getPhone, phone)
                .one();
    }

    @Override
    public User getByEmail(String email) {
        return lambdaQuery()
                .eq(User::getEmail, email)
                .one();
    }

    @Override
    public boolean existsByUsername(String username) {
        return lambdaQuery()
                .eq(User::getUsername, username)
                .exists();
    }

    @Override
    public boolean existsByPhone(String phone) {
        return lambdaQuery()
                .eq(User::getPhone, phone)
                .exists();
    }

    @Override
    public boolean existsByEmail(String email) {
        return lambdaQuery()
                .eq(User::getEmail, email)
                .exists();
    }
}
