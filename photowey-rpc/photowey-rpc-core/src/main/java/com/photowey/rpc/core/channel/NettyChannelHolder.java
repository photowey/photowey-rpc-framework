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
package com.photowey.rpc.core.channel;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;

/**
 * the Channel Holder
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public class NettyChannelHolder {
    /**
     * the Channel
     */
    private Channel channel;
    /**
     * the EventLoopGroup
     */
    private EventLoopGroup eventLoopGroup;

    public static NettyChannelHolder.NettyChannelHolderBuilder builder() {
        return new NettyChannelHolder.NettyChannelHolderBuilder();
    }

    public Channel getChannel() {
        return this.channel;
    }

    public EventLoopGroup getEventLoopGroup() {
        return this.eventLoopGroup;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setEventLoopGroup(EventLoopGroup eventLoopGroup) {
        this.eventLoopGroup = eventLoopGroup;
    }

    public NettyChannelHolder() {
    }

    public NettyChannelHolder(Channel channel, EventLoopGroup eventLoopGroup) {
        this.channel = channel;
        this.eventLoopGroup = eventLoopGroup;
    }

    public static class NettyChannelHolderBuilder {
        private Channel channel;
        private EventLoopGroup eventLoopGroup;

        NettyChannelHolderBuilder() {
        }

        public NettyChannelHolder.NettyChannelHolderBuilder channel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public NettyChannelHolder.NettyChannelHolderBuilder eventLoopGroup(EventLoopGroup eventLoopGroup) {
            this.eventLoopGroup = eventLoopGroup;
            return this;
        }

        public NettyChannelHolder build() {
            return new NettyChannelHolder(this.channel, this.eventLoopGroup);
        }
    }
}
