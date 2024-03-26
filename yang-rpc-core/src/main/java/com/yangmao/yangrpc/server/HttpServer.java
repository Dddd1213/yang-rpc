package com.yangmao.yangrpc.server;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
public interface HttpServer {
    /**
     * 启动http服务
     * @param port
     */
    void doStart(int port);
}
