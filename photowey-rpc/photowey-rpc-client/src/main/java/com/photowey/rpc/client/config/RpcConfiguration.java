package com.photowey.rpc.client.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.photowey.rpc.client.properties.RpcClientProperties;
import com.photowey.rpc.core.config.RpcCoreConfiguration;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.RpcConstants;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.List;

/**
 * the rpc configuration
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Configuration
@Import(value = {RpcCoreConfiguration.class})
@EnableConfigurationProperties(RpcClientProperties.class)
@ComponentScan(value = RpcConstants.RPC_CLIENT_BASE_PACKAGE)
public class RpcConfiguration {

    @Autowired
    private RpcClientProperties rpcClientProperties;

    @Bean
    public ZkClient zkClient() {
        RpcClientProperties.RpcClient client = rpcClientProperties.getClient();
        RpcClientProperties.RpcClient.RpcZookeeper zookeeper = client.getZookeeper();
        return new ZkClient(zookeeper.getConnectString(), client.getZookeeper().getSessionTimeout(), client.getZookeeper().getConnectionTimeout());
    }

    @Bean
    public LoadingCache<String, List<ProviderServiceInfo>> buildCache() {
        return CacheBuilder.newBuilder()
                .build(new CacheLoader<String, List<ProviderServiceInfo>>() {
                    @Override
                    public List<ProviderServiceInfo> load(String key) throws Exception {
                        return Collections.emptyList();
                    }
                });
    }
}
