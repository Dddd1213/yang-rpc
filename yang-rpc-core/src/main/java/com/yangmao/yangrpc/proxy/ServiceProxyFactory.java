package com.yangmao.yangrpc.proxy;

import java.lang.reflect.Proxy;

import com.yangmao.yangrpc.RpcApplication;
import com.yangmao.yangrpc.config.RpcConfig;
import com.yangmao.yangrpc.utils.ConfigUtils;

/**
 * todo 为什么用工厂模式
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-26
 */
public class ServiceProxyFactory {
    /**
     * 获取代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> serviceClass){

        if(RpcApplication.getRpcConfig().getMock()){
            return  (T) Proxy.newProxyInstance(
                    serviceClass.getClassLoader(),
                    new Class[]{serviceClass},
                    new MockServiceProxy()
            );
        }

        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }
}
