package com.yangmao.yangrpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.yangmao.yangrpc.RpcApplication;
import com.yangmao.yangrpc.config.RpcConfig;
import com.yangmao.yangrpc.model.RpcRequest;
import com.yangmao.yangrpc.model.RpcResponse;
import com.yangmao.yangrpc.serializer.Serializer;
import com.yangmao.yangrpc.serializer.SerializerFactory;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

/**
 * jdk动态代理，根据传过来的信息构造request，并通过http请求发送
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-26
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerialization());
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .args(args)
                .argTypes(method.getParameterTypes())
                .build();

        byte[] reqBodyBytes = serializer.serialize(rpcRequest);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8090")
                .body(reqBodyBytes)
                .execute();
        byte[] respBodyBytes = httpResponse.bodyBytes();
        RpcResponse rpcResponse = serializer.deserialize(respBodyBytes, RpcResponse.class);
        if (rpcResponse.getException() != null) {
            throw rpcResponse.getException();
        }

        return rpcResponse.getResult();
    }
}
