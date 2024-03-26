package com.yangmao.yangrpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-26
 */
public class MockServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        return getDefaultResult(returnType);
    }
    private Object getDefaultResult(Class<?> returnType) {
        if(returnType == int.class){
            return 0;
        }
        return null;
    }
}
