/*
 * Copyright © 2020 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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