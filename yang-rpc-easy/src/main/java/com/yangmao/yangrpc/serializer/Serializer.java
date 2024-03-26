package com.yangmao.yangrpc.serializer;

import java.io.IOException;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
public interface Serializer {
    /**
     * 序列化
     * @param obj
     * @return
     * @param <T>
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 反序列化
     * @param data
     * @param clazz
     * @return
     * @param <T>
     */
    <T> T deserialize(byte[] data, Class<T> clazz) throws IOException;
}
