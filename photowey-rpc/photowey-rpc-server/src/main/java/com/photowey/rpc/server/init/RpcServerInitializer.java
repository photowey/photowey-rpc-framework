package com.photowey.rpc.server.init;

import com.photowey.rpc.server.handler.RpcRequestHandler;
import com.photowey.rpc.server.handler.RpcServerDecodeHandler;
import com.photowey.rpc.server.handler.RpcServerEncodeHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RpcServerInitializer
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
@ChannelHandler.Sharable
public class RpcServerInitializer extends ChannelInitializer<Channel> {

    @Autowired
    private RpcRequestHandler rpcRequestHandler;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                .addLast(this.populateRpcServerDecodeHandler())
                .addLast(this.populateRpcServerEncodeHandler())
                .addLast(this.rpcRequestHandler);
    }

    private RpcServerDecodeHandler populateRpcServerDecodeHandler() {
        return new RpcServerDecodeHandler();
    }

    private RpcServerEncodeHandler populateRpcServerEncodeHandler() {
        return new RpcServerEncodeHandler();
    }
}
