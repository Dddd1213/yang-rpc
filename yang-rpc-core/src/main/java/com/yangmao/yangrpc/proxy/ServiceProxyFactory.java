package com.yangmao.yangrpc.proxy;

import java.lang.reflect.Proxy;

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
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }
}
