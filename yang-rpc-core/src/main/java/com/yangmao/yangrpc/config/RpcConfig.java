package com.yangmao.yangrpc.config;

import static com.yangmao.yangrpc.constant.RpcConstant.DEFAULT_CONFIG_PREFIX;

import com.yangmao.yangrpc.utils.ConfigUtils;

import lombok.Data;


/**
 * RPC框架配置
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-26
 */
@Data
public class RpcConfig {
    private String name = "yang-rpc";
    private String version = "1.0";
    private String serverHost = "localhost";
    private Integer serverPort = 8080;
    private Boolean mock = false;
    private RpcConfig(){};

    private static RpcConfig rpcConfig;
    public static RpcConfig getRpcConfig(){
        if(rpcConfig == null){
            synchronized (RpcConfig.class){
                if(rpcConfig == null){
                    rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, DEFAULT_CONFIG_PREFIX);
                }
            }
        }
        return rpcConfig;
    }

}
