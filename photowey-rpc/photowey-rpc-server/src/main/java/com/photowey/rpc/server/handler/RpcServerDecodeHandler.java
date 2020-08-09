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
