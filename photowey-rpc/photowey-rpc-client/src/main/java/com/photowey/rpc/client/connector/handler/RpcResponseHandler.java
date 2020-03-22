package com.photowey.rpc.client.connector.handler;

import com.photowey.rpc.client.request.RequestExecutor;
import com.photowey.rpc.core.response.RpcResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * the rpc response handler
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Component
@ChannelHandler.Sharable
public class RpcResponseHandler extends SimpleChannelInboundHandler<RpcResponse> {

    @Autowired
    private RequestExecutor requestExecutor;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        this.requestExecutor.notify(response.getSessionId(), response);
    }
}
