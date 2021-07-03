package com.yubin.homework.rpc.proxy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 11:40
 **/
public abstract class RpcProxy {
    private ConcurrentHashMap<String, Object> proxyCache = new ConcurrentHashMap<>();

    Object getProxy(String className) {
        return proxyCache.get(className);
    }

    Boolean isExit(String className) {
        return proxyCache.containsKey(className);
    }

    void add(String className, Object proxy) {
        proxyCache.put(className, proxy);
    }
}
