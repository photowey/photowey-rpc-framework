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
