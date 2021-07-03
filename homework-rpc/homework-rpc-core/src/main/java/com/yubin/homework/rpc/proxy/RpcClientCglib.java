package com.yubin.homework.rpc.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 11:43
 **/
public class RpcClientCglib extends RpcProxy implements RpcClient {
    @Override
    public <T> T create(Class<T> serviceClass, String url) {
        if (!isExit(serviceClass.getName())) {
            add(serviceClass.getName(), newProxy(serviceClass, url));
        }
        return (T) getProxy(serviceClass.getName());
    }

    private <T> T newProxy(Class<T> serviceClass, String url) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new RpcInvocationHandler(serviceClass, url));
        enhancer.setSuperclass(serviceClass);
        return (T) enhancer.create();
    }
}
