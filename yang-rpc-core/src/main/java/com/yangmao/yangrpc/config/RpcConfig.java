package com.yangmao.yangrpc.config;

import static com.yangmao.yangrpc.constant.RpcConstant.DEFAULT_CONFIG_PREFIX;
import static com.yangmao.yangrpc.constant.SerializerConstant.JDK_SERIALIZER;

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
    private Integer serverPort = 8090;
    private Boolean mock = false;
    private String serialization = JDK_SERIALIZER;

}
