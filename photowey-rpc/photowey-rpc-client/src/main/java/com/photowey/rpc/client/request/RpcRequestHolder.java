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
package com.photowey.rpc.client.request;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.photowey.rpc.client.cache.ServiceRouteCache;
import com.photowey.rpc.client.connector.RpcClientConnector;
import com.photowey.rpc.client.properties.RpcClientProperties;
import com.photowey.rpc.core.channel.NettyChannelHolder;
import com.photowey.rpc.core.cluster.ClusterEngine;
import com.photowey.rpc.core.cluster.strategy.ClusterStrategySelector;
import com.photowey.rpc.core.enums.ClusterStrategyEnum;
import com.photowey.rpc.core.enums.RpcStatus;
import com.photowey.rpc.core.exception.RpcException;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.util.RpcUtils;
import com.photowey.rpc.core.util.SystemOutUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

/**
 * the RpcRequest Holder
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Component
public class RpcRequestHolder {

    private static final Logger log = LoggerFactory.getLogger(RpcRequestHolder.class);

    private static final ConcurrentHashMap<String, NettyChannelHolder> CHANNEL_HOLDER_CACHE = new ConcurrentHashMap<>();

    private static final ExecutorService REQUEST_EXECUTOR = new ThreadPoolExecutor(
            30,
            100,
            0,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(30),
            new ThreadFactoryBuilder().setNameFormat("rpc-request-connector-%d").build()
    );

    // ==================================================================================== INIT

    @Autowired
    private RpcRequestExecutor rpcRequestExecutor;
    @Autowired
    private ServiceRouteCache serviceRouteCache;
    @Autowired
    private RpcClientProperties rpcClientProperties;
    @Autowired
    private ClusterEngine clusterEngine;

    // ====================================================================================

    public void doRequest(RpcRequest request) throws InterruptedException, RpcException {
        String clusterStrategy = ClusterStrategyEnum.fromName(this.rpcClientProperties.getCluster().getStrategy()).toName();
        ClusterStrategySelector strategy = this.clusterEngine.determineClusterStrategy(clusterStrategy);
        List<ProviderServiceInfo> providerServices = this.serviceRouteCache.getServiceRoutes(request.getClassName());
        ProviderServiceInfo targetServiceProvider = strategy.select(providerServices);

        if (targetServiceProvider != null) {
            String sessionId = request.getSessionId();
            CountDownLatch latch = new CountDownLatch(1);
            REQUEST_EXECUTOR.execute(new RpcClientConnector(sessionId, targetServiceProvider, latch));

            latch.await();

            NettyChannelHolder channelHolder = CHANNEL_HOLDER_CACHE.get(sessionId);
            channelHolder.getChannel().writeAndFlush(request);
            log.info("send rpc request:[{}.{}] to service provider successfully", sessionId, request.getClassName(), request.getMethodName());
        } else {
            throw new RpcException(RpcStatus.NO_AVAILABLE.toValue(), SystemOutUtils.format("this service:[{}] not available", request.getClassName()));
        }
    }

    public void registerChannelHolder(String sessionId, NettyChannelHolder channelHolder) {
        if (RpcUtils.isEmpty(sessionId) || RpcUtils.isEmpty(channelHolder)) {
            return;
        }
        CHANNEL_HOLDER_CACHE.put(sessionId, channelHolder);
        log.info("--- >>> register the channel holder[{}:{}] successfully...", sessionId, channelHolder.toString());

        this.rpcRequestExecutor.submit(sessionId, channelHolder.getChannel().eventLoop());
        log.info("--- >>> submit request into rpc request holder successfully...");
    }

    // ====================================================================================

    public void destroyChannelHolder(String sessionId) {
        if (RpcUtils.isEmpty(sessionId)) {
            return;
        }
        NettyChannelHolder channelHolder = CHANNEL_HOLDER_CACHE.remove(sessionId);
        try {
            channelHolder.getChannel().closeFuture();
            channelHolder.getEventLoopGroup().shutdownGracefully();
        } catch (Exception e) {
            log.error("--- >>> handle close the netty channel holder exception.the sessionId:[{}]", sessionId, e);
        }
        log.info("--- >>> destroy NettyChannelHolder[{}] successfully <<< ---", sessionId);
    }
}
