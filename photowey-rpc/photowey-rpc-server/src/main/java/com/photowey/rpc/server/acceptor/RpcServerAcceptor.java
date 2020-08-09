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
package com.photowey.rpc.server.acceptor;

import com.photowey.rpc.server.init.RpcServerInitializer;
import com.photowey.rpc.server.properties.RpcServerProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RpcServerAcceptor
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
public class RpcServerAcceptor implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(RpcServerAcceptor.class);

    private EventLoopGroup server = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();

    private RpcServerProperties rpcServerProperties;
    private RpcServerInitializer rpcServerInitializer;

    public RpcServerAcceptor() {
    }

    public RpcServerAcceptor(RpcServerProperties rpcServerProperties, RpcServerInitializer rpcServerInitializer) {
        this.rpcServerProperties = rpcServerProperties;
        this.rpcServerInitializer = rpcServerInitializer;
    }

    @Override
    public void run() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(server, worker)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(this.rpcServerInitializer);
        try {
            ChannelFuture future = bootstrap.bind(this.rpcServerProperties.getNetwork().getPort()).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("ZnsServer acceptor startup failure!", e);
            e.printStackTrace();
        } finally {
            server.shutdownGracefully().syncUninterruptibly();
            worker.shutdownGracefully().syncUninterruptibly();
        }
    }
}
