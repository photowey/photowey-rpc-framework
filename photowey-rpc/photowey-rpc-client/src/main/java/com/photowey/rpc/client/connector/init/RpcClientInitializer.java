package com.photowey.rpc.client.connector.init;

import com.photowey.rpc.client.connector.handler.RpcClientDecodeHandler;
import com.photowey.rpc.client.connector.handler.RpcClientEncodeHandler;
import com.photowey.rpc.client.connector.handler.RpcResponseHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * the rpc client initializer
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Component
@ChannelHandler.Sharable
public class RpcClientInitializer extends ChannelInitializer<Channel> {

    @Autowired
    private RpcResponseHandler rpcResponseHandler;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                .addLast(new RpcClientEncodeHandler())
                .addLast(new RpcClientDecodeHandler())
                .addLast(this.rpcResponseHandler);
    }
}
