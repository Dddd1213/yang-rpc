package com.yangmao.example.consumer;


import static com.yangmao.yangrpc.constant.RpcConstant.DEFAULT_CONFIG_PREFIX;

import com.yangmao.yangrpc.config.RpcConfig;
import com.yangmao.yangrpc.utils.ConfigUtils;

import cn.hutool.core.io.FileUtil;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-26
 */
public class ConsumerExample {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++){
            System.out.println(ConfigUtils.loadConfig(RpcConfig.class, DEFAULT_CONFIG_PREFIX));
            Thread.sleep(5000);
        }

    }
}
