package com.yangmao.yangrpc.server;

import java.io.IOException;
import java.lang.reflect.Method;

import com.yangmao.yangrpc.model.RpcRequest;
import com.yangmao.yangrpc.model.RpcResponse;
import com.yangmao.yangrpc.registry.LocalRegistry;
import com.yangmao.yangrpc.serializer.Serializer;
import com.yangmao.yangrpc.serializer.impl.JdkSerializer;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {

    /**
     * 实现handler接口目的是为了定义回调函数handle，每次有新的http请求到达时被调用
     * @param req
     */
    public void handle(HttpServerRequest req) {
        System.out.println("Received request: "+req.method()+" "+req.uri());
        RpcResponse rpcResponse = new RpcResponse();

        //反序列化
        final JdkSerializer jdkSerializer = new JdkSerializer();
        //注意：这一大段都要写在handler里面！！！
        req.bodyHandler(buffer -> {
            System.out.println("进入handle");
            RpcRequest rpcRequest = null;
            byte[] bytes = buffer.getBytes();
            try {
                rpcRequest = jdkSerializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
                doResponse(req,rpcResponse,jdkSerializer);
            }

        //如果请求为null，直接返回
        if(rpcRequest ==null){
           rpcResponse.setMessage("rpcRequest is null");
           doResponse(req,rpcResponse,jdkSerializer);
       }
        //如果请求不为null，则进行反射调用
        try {
            Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
            Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getArgTypes());
            //正常来说，这里什么实例，调用什么方法都是写死的
            //这里相当于，通过传递进来的类名，方法名，参数等等信息，从注册中心获取到对应的类，动态地执行对应的方法（感觉很像动态代理的思想）
            Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());

            rpcResponse.setResult(result);
            rpcResponse.setResultType(method.getReturnType());
            rpcResponse.setMessage("ok!");
        } catch (Exception e) {
            e.printStackTrace();
            rpcResponse.setMessage(e.getMessage());
            rpcResponse.setException(e);
        }
        doResponse(req,rpcResponse,jdkSerializer);
        });
    }

    private void doResponse(HttpServerRequest req,RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = req.response()
                .putHeader("content-type", "text/json");
        //序列化
        try {
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }

}
