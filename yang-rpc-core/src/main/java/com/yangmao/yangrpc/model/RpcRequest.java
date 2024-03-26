package com.yangmao.yangrpc.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    private String serviceName;
    private String methodName;
    private Object[] args;
    private Class<?>[] argTypes;
}
