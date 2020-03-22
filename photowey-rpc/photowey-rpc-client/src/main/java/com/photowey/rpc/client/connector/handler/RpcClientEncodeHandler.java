package com.photowey.rpc.client.connector.handler;

import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.serializer.ProtostuffSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * the rpc client encode handler
 * TODO custom the photo protocol
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public class RpcClientEncodeHandler extends MessageToByteEncoder<RpcRequest> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcRequest request, ByteBuf in) throws Exception {
        ProtostuffSerializer protostuffSerializer = SpringContextHolder.getBean(ProtostuffSerializer.class);
        byte[] bytes = protostuffSerializer.serialize(request);
        in.writeInt(bytes.length);
        in.writeBytes(bytes);
    }
}
