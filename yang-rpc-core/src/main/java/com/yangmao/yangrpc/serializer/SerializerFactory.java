package com.yangmao.yangrpc.serializer;

import static com.yangmao.yangrpc.constant.SerializerConstant.JDK_SERIALIZER;
import static com.yangmao.yangrpc.constant.SerializerConstant.JSON_SERIALIZER;

import java.util.Map;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;

import com.yangmao.yangrpc.serializer.impl.JdkSerializer;
import com.yangmao.yangrpc.serializer.impl.JsonSerializer;
import com.yangmao.yangrpc.spi.SpiLoader;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-27
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    public static Serializer getInstance(String key){
        return SpiLoader.getInstance(Serializer.class,key);
    }
}
