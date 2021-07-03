package com.yubin.homework.rpc.netty.client;

import com.alibaba.fastjson.JSON;
import com.yubin.homework.rpc.api.RpcRequest;
import com.yubin.homework.rpc.api.RpcResponse;
import com.yubin.homework.rpc.netty.rpc.RpcProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @program: homework
 * @description:自定义解码器
 * @author: Yu Bin
 * @create: 2021-07-02 20:52
 **/
@Slf4j
public class NettyClientSync {
    private static NettyClientSync clientSync;

    private NettyClientSync() {
    }

    public static NettyClientSync getInstance() {
        if (clientSync == null) {
            synchronized (NettyClientSync.class) {
                if (clientSync == null) {
                    clientSync = new NettyClientSync();
                }
            }
        }
        return clientSync;
    }

    public Channel createChannel(String address, int port) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        //创建从线程组，处理主线程组分配下来的io操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.AUTO_CLOSE, true)
                .handler(new ClientInitializer());
        return bootstrap.connect(address, port).sync().channel();
    }

    public RpcResponse getResponse(RpcRequest rpcRequest, String url) throws URISyntaxException, InterruptedException {
        URI uri = new URI(url);
        RpcProtocol protocol = convertNettyRequest(rpcRequest);
        ClientSyncHandler handler = new ClientSyncHandler();
        Channel channel = createChannel(uri.getHost(), uri.getPort());
        channel.pipeline().addFirst(handler);
        channel.writeAndFlush(protocol).sync();
        return handler.getResponse();
    }

    private RpcProtocol convertNettyRequest(RpcRequest rpcRequest) {
        String requestJson = JSON.toJSONString(rpcRequest);
        RpcProtocol request = RpcProtocol.builder().len(requestJson.getBytes(CharsetUtil.UTF_8).length).content(requestJson.getBytes(CharsetUtil.UTF_8)).build();
        return request;
    }
}
