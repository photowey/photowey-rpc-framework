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
