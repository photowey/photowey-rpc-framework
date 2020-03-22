package com.photowey.rpc.server.handler;

import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.response.RpcResponse;
import com.photowey.rpc.core.serializer.ProtostuffSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * RpcServerEncodeHandler
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
public class RpcServerEncodeHandler extends MessageToByteEncoder<RpcResponse> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RpcResponse rpcResponse, ByteBuf out) throws Exception {
        ProtostuffSerializer protostuffSerializer = SpringContextHolder.getBean(ProtostuffSerializer.class);
        byte[] bytes = protostuffSerializer.serialize(rpcResponse);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
