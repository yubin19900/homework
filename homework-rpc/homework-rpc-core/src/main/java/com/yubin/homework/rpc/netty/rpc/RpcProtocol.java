package com.yubin.homework.rpc.netty.rpc;

import lombok.Builder;
import lombok.Data;

/**
 * @program: homework
 * @description:自定义编码器
 * @author: Yu Bin
 * @create: 2021-07-02 21:57
 **/
@Data
@Builder
public class RpcProtocol {

    /**
     * 数据大小
     */
    private int len;

    /**
     * 数据内容
     */
    private byte[] content;
}
