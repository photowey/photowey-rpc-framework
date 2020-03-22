package com.photowey.rpc.server.handler;

import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.request.DefaultRpcRequest;
import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.serializer.ProtostuffSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * RpcServerDecodeHandler
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
public class RpcServerDecodeHandler extends ByteToMessageDecoder {
    public static final int HEADER_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() <= HEADER_LENGTH) {
            return;
        }

        int length = in.readInt();
        in.markReaderIndex();
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
        } else {
            byte[] bytes = new byte[in.readableBytes()];
            in.readBytes(bytes);

            ProtostuffSerializer protostuffSerializer = SpringContextHolder.getBean(ProtostuffSerializer.class);
            RpcRequest rpcRequest = protostuffSerializer.deserialize(bytes, DefaultRpcRequest.class);
            out.add(rpcRequest);
        }
    }
}
