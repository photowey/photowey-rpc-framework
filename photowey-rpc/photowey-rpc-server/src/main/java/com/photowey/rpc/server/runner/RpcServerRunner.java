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
package com.photowey.rpc.server.runner;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.photowey.rpc.server.acceptor.RpcServerAcceptor;
import com.photowey.rpc.server.init.RpcServerInitializer;
import com.photowey.rpc.server.properties.RpcServerProperties;
import com.photowey.rpc.server.register.ServiceRegistrar;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

/**
 * RpServerRunner
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
public class RpcServerRunner implements DisposableBean {

    @Autowired
    private RpcServerProperties rpcServerProperties;

    @Autowired
    private ServiceRegistrar serviceRegistrar;
    @Autowired
    private RpcServerInitializer rpcServerInitializer;

    private ExecutorService executor = null;

    @PostConstruct
    public void init() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Rpc-Server-%s").build();
        this.executor = new ThreadPoolExecutor(
                this.rpcServerProperties.getServer().getCoreSize(),
                this.rpcServerProperties.getServer().getMaxSize(),
                30L,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    public void run() {
        this.executor.execute(this.populateRpcServerAcceptor());
        this.serviceRegistrar.register();
    }

    public RpcServerAcceptor populateRpcServerAcceptor() {
        return new RpcServerAcceptor(this.rpcServerProperties, this.rpcServerInitializer);
    }

    @Override
    public void destroy() throws Exception {
        this.executor.shutdown();
    }
}
