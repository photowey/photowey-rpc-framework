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
package com.photowey.rpc.server.config;

import com.photowey.rpc.core.config.RpcBaseCoreConfiguration;
import com.photowey.rpc.core.util.RpcConstants;
import com.photowey.rpc.server.properties.RpcServerProperties;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * RpcServerConfiguration
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Configuration
@ComponentScan(value = RpcConstants.RPC_SERVER_BASE_PACKAGE)
@EnableConfigurationProperties(RpcServerProperties.class)
@Import(value = {
        RpcBaseCoreConfiguration.class
})
public class RpcServerConfiguration {

    @Autowired
    private RpcServerProperties rpcServerProperties;

    @Bean
    public ZkClient zkClient() {
        return new ZkClient(
                this.rpcServerProperties.getZk().getConnectString(),
                this.rpcServerProperties.getZk().getSessionTimeout(),
                this.rpcServerProperties.getZk().getConnectionTimeout()
        );
    }
}
