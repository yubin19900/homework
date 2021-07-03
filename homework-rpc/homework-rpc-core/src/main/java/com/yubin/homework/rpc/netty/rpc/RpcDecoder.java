package com.yubin.homework.rpc.netty.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @program: homework
 * @description:自定义解码器
 * @author: Yu Bin
 * @create: 2021-07-02 20:52
 **/
@Slf4j
public class RpcDecoder extends ByteToMessageDecoder {
    private int length = 0;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= 8) {
            if (length == 0) {
                length = byteBuf.readInt();
            }

            if (byteBuf.readableBytes() < length) {
                log.info("data is less than length");
                return;
            }

            byte[] content = new byte[length];
            if (byteBuf.readableBytes() >= length) {
                byteBuf.readBytes(content);
                RpcProtocol protocol = RpcProtocol.builder().len(length).content(content).build();
                list.add(protocol);
            }
            length = 0;
        }
    }
}
