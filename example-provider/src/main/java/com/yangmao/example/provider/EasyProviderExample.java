package com.yangmao.example.provider;

import com.yangmao.example.common.service.UserService;
import com.yangmao.yangrpc.RpcApplication;
import com.yangmao.yangrpc.registry.LocalRegistry;
import com.yangmao.yangrpc.server.VertxHttpServer;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
public class EasyProviderExample {
    public static void main(String[] args) {

        RpcApplication.init();

        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        VertxHttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
