package com.yangmao.example.consumer;

import com.yangmao.example.common.model.User;
import com.yangmao.example.common.service.UserService;
import com.yangmao.yangrpc.proxy.ServiceProxyFactory;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("yangmao");
        //如果能成功远程调用，将会打印出"Hihi, 我是yangmao"
        User newUser = userService.getUser(user);
        if(newUser!=null){
            System.out.println(user.getName());
        }else{
            System.out.println("newUser is null");
        }
//        System.out.println(userService.getNumber());
    }
}
