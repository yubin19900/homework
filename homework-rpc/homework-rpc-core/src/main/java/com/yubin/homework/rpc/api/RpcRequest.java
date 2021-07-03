package com.yubin.homework.rpc.api;

import lombok.Builder;
import lombok.Data;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-02 20:39
 **/
@Data
@Builder
public class RpcRequest {

    /**
     * 接口类名称
     */
    private String serviceClass;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private Object[] args;
}
