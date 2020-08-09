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
package com.photowey.rpc.client.connector;

import com.photowey.rpc.client.connector.init.RpcClientInitializer;
import com.photowey.rpc.client.request.RpcRequestHolder;
import com.photowey.rpc.core.channel.NettyChannelHolder;
import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * the rpc client connector
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public class RpcClientConnector implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(RpcClientConnector.class);

    private String sessionId;
    private ProviderServiceInfo providerServiceInfo;
    private CountDownLatch countDownLatch;
    private RpcClientInitializer rpcClientInitializer;

    public RpcClientConnector(String sessionId, ProviderServiceInfo providerServiceInfo, CountDownLatch countDownLatch) {
        this.sessionId = sessionId;
        this.providerServiceInfo = providerServiceInfo;
        this.countDownLatch = countDownLatch;
        this.rpcClientInitializer = SpringContextHolder.getBean(RpcClientInitializer.class);
    }

    @Override
    public void run() {
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = this.populateBootstrap(worker);
        try {
            ChannelFuture future = bootstrap.connect().sync();
            if (future.isSuccess()) {
                NettyChannelHolder channelHolder = this.populateNettyChannelHolder(worker, future);
                RpcRequestHolder rpcRequestHolder = SpringContextHolder.getBean(RpcRequestHolder.class);
                rpcRequestHolder.registerChannelHolder(this.sessionId, channelHolder);
                log.info("construct a connector with service provider[{}:{}] successfully",
                        this.providerServiceInfo.getServerIp(),
                        this.providerServiceInfo.getNetworkPort()
                );

                this.countDownLatch.countDown();
            }
        } catch (InterruptedException e) {
            log.error("execute rpc connect exception,the sessionId:[{}]", this.sessionId, e);
        }
    }

    private NettyChannelHolder populateNettyChannelHolder(EventLoopGroup worker, ChannelFuture future) {
        return NettyChannelHolder.builder()
                .channel(future.channel())
                .eventLoopGroup(worker)
                .build();
    }

    private Bootstrap populateBootstrap(EventLoopGroup worker) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .remoteAddress(this.providerServiceInfo.getServerIp(), this.providerServiceInfo.getNetworkPort())
                .handler(this.rpcClientInitializer);
        return bootstrap;
    }
}
