package com.yubin.homework.rpc.netty.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yubin.homework.rpc.api.RpcRequest;
import com.yubin.homework.rpc.api.RpcResponse;
import com.yubin.homework.rpc.netty.rpc.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-02 21:17
 **/
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<RpcProtocol> {

    private ApplicationContext applicationContext;

    public ServerHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol msg) throws Exception {
        RpcRequest rpcRequest = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8), RpcRequest.class);
        log.info("NettyServer serializer:{}", rpcRequest.toString());
        RpcResponse response = invoke(rpcRequest);

        String responseJson = JSON.toJSONString(response);
        RpcProtocol protocol = RpcProtocol.builder().len(responseJson.getBytes(CharsetUtil.UTF_8).length).content(responseJson.getBytes(CharsetUtil.UTF_8)).build();
        ctx.writeAndFlush(protocol).sync();
    }

    private RpcResponse invoke(RpcRequest rpcRequest) {
        RpcResponse response = new RpcResponse();

        String serviceClass = rpcRequest.getServiceClass();
        Object service = applicationContext.getBean(serviceClass);

        Method method = resolveMethodFromClass(service.getClass(), rpcRequest.getMethod());
        try {
            Object result = method.invoke(service, rpcRequest.getArgs());
            String resultJson = JSON.toJSONString(result, SerializerFeature.WriteClassName);
            log.info("method invoke result:{}", resultJson);
            response.setResult(resultJson);
            response.setStatus(true);
        } catch (IllegalAccessException | InvocationTargetException e) {
            response.setException(e);
            response.setStatus(false);
            e.printStackTrace();
        }
        return response;
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}
