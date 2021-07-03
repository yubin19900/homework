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
public class RpcResponse {

    /**
     * 响应体
     */
    private Object result;

    /**
     * 是否成功
     */
    private boolean status;

    /**
     * 异常
     */
    private Exception exception;
}
