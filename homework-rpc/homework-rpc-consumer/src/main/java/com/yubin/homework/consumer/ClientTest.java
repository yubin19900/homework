package com.yubin.homework.consumer;

import com.alibaba.fastjson.JSON;
import com.yubin.homework.api.OrderService;
import com.yubin.homework.rpc.proxy.RpcClient;
import com.yubin.homework.rpc.proxy.RpcClientCglib;
import com.yubin.homework.rpc.proxy.RpcClientJdk;

import java.util.Map;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 13:35
 **/
public class ClientTest {

    private OrderService orderService;

    public static void main(String[] args) {

//        RpcClient jdk = new RpcClientJdk();
//        OrderService orderService = jdk.create(OrderService.class, "http://localhost:8088/");
//        Map<String, Object> map = orderService.selectOrderById(1);
//        if (map != null) {
//            System.out.println(JSON.toJSONString(map));
//        }

        RpcClientCglib cglib = new RpcClientCglib();
        OrderService orderService = cglib.create(OrderService.class, "http://localhost:8088/");
        Map<String, Object> map = orderService.selectOrderById(1);
        if (map != null) {
            System.out.println(JSON.toJSONString(map));
        }
    }
}
