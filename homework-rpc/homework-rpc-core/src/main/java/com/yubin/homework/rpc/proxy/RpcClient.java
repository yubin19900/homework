package com.yubin.homework.rpc.proxy;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 11:02
 **/
public interface RpcClient {
    /**
     * @param serviceClass
     * @param url
     * @param <T>
     * @return
     */
    <T> T create(Class<T> serviceClass, String url);
}
