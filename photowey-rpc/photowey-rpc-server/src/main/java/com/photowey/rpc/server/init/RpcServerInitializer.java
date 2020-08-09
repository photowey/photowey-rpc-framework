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
