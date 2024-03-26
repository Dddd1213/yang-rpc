package com.yangmao.yangrpc.utils;

import cn.hutool.setting.dialect.Props;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-26
 */
public class ConfigUtils {

    public static <T> T loadConfig(Class<T> tClass,String prefix){
        return loadConfig(tClass,prefix,"");
    }
    public static <T> T loadConfig(Class<T> tClass,String prefix,String environment){
        StringBuilder configFileBuilder = new StringBuilder("application");
        if(!environment.equals("")){
            configFileBuilder.append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass,prefix);
    }

}
