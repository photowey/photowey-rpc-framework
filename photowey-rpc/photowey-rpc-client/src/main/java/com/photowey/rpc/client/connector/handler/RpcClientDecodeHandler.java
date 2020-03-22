package com.photowey.rpc.client.connector.handler;

import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.response.DefaultRpcResponse;
import com.photowey.rpc.core.response.RpcResponse;
import com.photowey.rpc.core.serializer.ProtostuffSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * the rpc client decode handler
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public class RpcClientDecodeHandler extends ByteToMessageDecoder {

    public static final int HEADER_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        if (in.readableBytes() <= HEADER_LENGTH) {
            return;
        }
        int length = in.readInt();
        in.markReaderIndex();
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
        } else {
            byte[] content = new byte[in.readableBytes()];
            in.readBytes(content);
            ProtostuffSerializer protostuffSerializer = SpringContextHolder.getBean(ProtostuffSerializer.class);
            RpcResponse response = protostuffSerializer.deserialize(content, DefaultRpcResponse.class);
            list.add(response);
        }
    }
}
