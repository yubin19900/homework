package com.yubin.homework.rpc.proxy;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.yubin.homework.rpc.api.RpcRequest;
import com.yubin.homework.rpc.api.RpcResponse;
import com.yubin.homework.rpc.netty.client.NettyClientSync;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 11:19
 **/
@Slf4j
public class RpcInvocationHandler implements InvocationHandler, MethodInterceptor {
    private Class<?> serviceClass;
    private String url;

    <T> RpcInvocationHandler(Class<T> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        return process(serviceClass, method, args, url);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
        return process(serviceClass, method, objects, url);
    }

    private Object process(Class<?> service, Method method, Object[] params, String url) {
        RpcRequest rpcRequest = new RpcRequest().setServiceClass(service.getName()).setMethod(method.getName()).setArgs(params);
        RpcResponse rpcResponse = null;
        try {
            rpcResponse = NettyClientSync.getInstance().getResponse(rpcRequest, url);
        } catch (InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }

        if (rpcResponse != null) {
            if (rpcResponse.isStatus()) {
                return JSON.parse(rpcResponse.getResult().toString());
            } else {
                log.warn("client exception");
            }
        }
        return null;
    }
}
