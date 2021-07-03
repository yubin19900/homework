package com.yubin.homework.rpc.netty.client;

import com.alibaba.fastjson.JSON;
import com.yubin.homework.rpc.api.RpcResponse;
import com.yubin.homework.rpc.netty.rpc.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: homework
 * @description:自定义解码器
 * @author: Yu Bin
 * @create: 2021-07-02 20:52
 **/
@Slf4j
public class ClientSyncHandler extends SimpleChannelInboundHandler<RpcProtocol> {

    private RpcResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol msg) {
        log.info("netty client receive message:{}", JSON.toJSONString(msg));

        // 将 RpcResponse字符串 反序列化成 RpcResponse对象
        RpcResponse rpcfxResponse = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8), RpcResponse.class);
        log.info("netty client serializer : " + rpcfxResponse.toString());
        response = rpcfxResponse;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public RpcResponse getResponse() {
        return response;
    }
}
