package com.yubin.homework.rpc.netty.server;

import com.yubin.homework.rpc.netty.rpc.RpcDecoder;
import com.yubin.homework.rpc.netty.rpc.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-02 21:10
 **/
@Slf4j
@Component
public class NettyServer {
    private ApplicationContext applicationContext;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public NettyServer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void run() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        int threadSize = Runtime.getRuntime().availableProcessors();
        //创建主线程组，接收请求
        bossGroup = new NioEventLoopGroup(threadSize);
        //创建从线程组，处理主线程组分配下来的io操作
        workerGroup = new NioEventLoopGroup(threadSize * 2);
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast("RpcEncoder", new RpcEncoder());
                        pipeline.addLast("RpcDecoder", new RpcDecoder());
                        pipeline.addLast("Message Handler", new ServerHandler(applicationContext));
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.bind(8088).sync();
            log.info("netty server start");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        // 释放资源
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
