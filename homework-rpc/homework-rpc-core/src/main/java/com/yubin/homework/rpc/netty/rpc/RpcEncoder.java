package com.yubin.homework.rpc.netty.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: homework
 * @description:自定义编码器
 * @author: Yu Bin
 * @create: 2021-07-02 20:57
 **/
@Slf4j
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol rpcProtocol, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(rpcProtocol.getLen()).writeBytes(rpcProtocol.getContent());
    }
}
