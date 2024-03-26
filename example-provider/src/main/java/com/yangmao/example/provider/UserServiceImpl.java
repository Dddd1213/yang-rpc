package com.yangmao.example.provider;

import com.yangmao.example.common.model.User;
import com.yangmao.example.common.service.UserService;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
public class UserServiceImpl implements UserService
{
    public User getUser(User user) {
        System.out.println("Hihi, 我是"+user.getName());
        return user;
    }
}
