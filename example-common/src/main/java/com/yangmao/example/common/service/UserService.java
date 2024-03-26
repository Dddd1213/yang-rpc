package com.yangmao.example.common.service;

import com.yangmao.example.common.model.User;

/**
 * 用户服务
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param user
     * @return
     */
    User getUser(User user);
}
