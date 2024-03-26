package com.yangmao.yangrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  本地注册中心
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
public class LocalRegistry {
    private static final Map<String,Class<?>> mp = new ConcurrentHashMap<>();

    public static void register(String serviceName,Class<?> implClass){
        mp.put(serviceName,implClass);
    }
    public static Class<?> get(String serviceName){
        return mp.get(serviceName);
    }

    public static void remove(String serviceName){
        mp.remove(serviceName);
    }


}
