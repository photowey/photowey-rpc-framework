package com.photowey.rpc.server.handler;

import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.response.DefaultRpcResponse;
import com.photowey.rpc.core.response.RpcResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * RpcRequestHandler
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
@ChannelHandler.Sharable
public class RpcRequestHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private static final Logger log = LoggerFactory.getLogger(RpcRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest rpcRequest) throws Exception {
        RpcResponse rpcResponse = new DefaultRpcResponse();

        String sessionId = rpcRequest.getSessionId();
        String className = rpcRequest.getClassName();
        String methodName = rpcRequest.getMethodName();
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] parameterValues = rpcRequest.getParameters();
        rpcResponse.setSessionId(sessionId);
        log.info("handle rpc request, the sessionId:[{}],className:[{}],methodName:[{}]", sessionId, className, methodName);
        try {
            Object targetClass = SpringContextHolder.getBean(Class.forName(className));
            Method targetMethod = targetClass.getClass().getMethod(methodName, parameterTypes);
            Object result = targetMethod.invoke(targetClass, parameterValues);
            rpcResponse.setRpcResult(result);
        } catch (Throwable cause) {
            rpcResponse.setRpcMessage(cause.getMessage());
        }

        ctx.writeAndFlush(rpcResponse);
    }
}