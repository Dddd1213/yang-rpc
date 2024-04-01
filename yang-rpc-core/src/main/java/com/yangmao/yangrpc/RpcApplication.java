package com.yangmao.yangrpc;

import static com.yangmao.yangrpc.constant.RpcConstant.DEFAULT_CONFIG_PREFIX;

import com.yangmao.yangrpc.config.RpcConfig;
import com.yangmao.yangrpc.utils.ConfigUtils;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-04-01
 */
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig newRpcConfig){
        rpcConfig = newRpcConfig;
    }

    public static void init(){
        RpcConfig newRpcConfig;
        newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, DEFAULT_CONFIG_PREFIX);
        init(newRpcConfig);
    }

    public static RpcConfig getRpcConfig(){
        if(rpcConfig == null){
            synchronized (RpcApplication.class){
                if(rpcConfig == null){
                    init();
                }
            }
        }
        return rpcConfig;
    }

}
