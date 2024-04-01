package com.yangmao.yangrpc.spi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yangmao.yangrpc.serializer.Serializer;

import cn.hutool.core.io.resource.ResourceUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * todo 没太理解
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-04-01
 */
@Slf4j
public class SpiLoader {
    /**
     * 接口名 => (key => 实现类)
     */
    private static Map<String, Map<String,Class<?>>> loaderMap = new ConcurrentHashMap<>();

    /**
     * 对象实例缓存，类路径 => 对象实例
     */
    private static Map<String,Object> instanceCache = new ConcurrentHashMap<>();

    private static final String RPC_SYSTEM_SPI_DIR = "META_INF/rpc/system";

    private static final String RPC_CUSTOM_SPI_DIR = "META_INF/rpc/custom";

    /**
     * 扫描目录
     */
    private static final String[] SCANS_DIRS = new String[]{RPC_SYSTEM_SPI_DIR,RPC_CUSTOM_SPI_DIR};

    public static Map<String,Class<?>> load(Class<?> loadClass){
        Map<String,Class<?>> keyClassMap = new HashMap<>();
        for (String scanDir : SCANS_DIRS) {
            List<URL> resources = ResourceUtil.getResources(scanDir + loadClass.getName());
            for(URL resource:resources){
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(resource.openStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    while((line = bufferedReader.readLine())!=null){
                        String[] strArray = line.split("=");
                        if(strArray.length>1){
                            keyClassMap.put(strArray[0],Class.forName(strArray[1]));
                        }
                    }
                } catch (IOException|ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        loaderMap.put(loadClass.getName(),keyClassMap);
        return keyClassMap;
    }

    public static <T> T getInstance(Class<?> tClass,String key){
        String tClassName = tClass.getName();
        Map<String, Class<?>> keyClassMap = loaderMap.get(tClassName);
        if(keyClassMap== null){
            throw new RuntimeException(String.format("SpiLoader未加载 %s 类型",tClassName));
        }
        if(!keyClassMap.containsKey(key)){
            throw new RuntimeException(String.format("SpiLoader的 %s 不存在 key = %s 的类型",tClassName,key));
        }
        Class<?> implClass = keyClassMap.get(key);
        String implClassName = implClass.getName();
        if(!instanceCache.containsKey(implClassName)){
            try {
                instanceCache.put(implClassName,implClass.newInstance());
            } catch (InstantiationException|IllegalAccessException e) {
                throw new RuntimeException(String.format("%s类实例化失败",implClassName),e);
            }
        }
        return (T) instanceCache.get(implClassName);

    }


}
