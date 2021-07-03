package com.yubin.homework.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 11:39
 **/
public class RpcClientJdk extends RpcProxy implements RpcClient {
    @Override
    public <T> T create(Class<T> serviceClass, String url) {
        if (!isExit(serviceClass.getName())) {
            add(serviceClass.getName(), newProxy(serviceClass, url));
        }
        return (T) getProxy(serviceClass.getName());
    }

    private <T> T newProxy(Class<T> serviceClass, String url) {
        ClassLoader loader = RpcClientJdk.class.getClassLoader();
        Class[] classes = new Class[]{serviceClass};
        return (T) Proxy.newProxyInstance(loader, classes, new RpcInvocationHandler(serviceClass, url));
    }
}
