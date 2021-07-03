package com.yubin.homework.rpc.netty.client;

import com.yubin.homework.rpc.netty.rpc.RpcDecoder;
import com.yubin.homework.rpc.netty.rpc.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-02 20:52
 **/
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("MessageEncoder", new RpcEncoder());
        pipeline.addLast("MessageDecoder", new RpcDecoder());
        pipeline.addLast("clientHandler", new ClientSyncHandler());
    }
}
