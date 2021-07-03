package com.yubin.homework.rpc.api;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-02 20:39
 **/
@Data
@Accessors(chain = true)
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
