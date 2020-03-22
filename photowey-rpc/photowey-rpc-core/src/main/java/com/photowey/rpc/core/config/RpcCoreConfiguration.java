package com.photowey.rpc.core.config;

import com.photowey.rpc.core.cluster.ClusterEngine;
import com.photowey.rpc.core.util.RpcConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * the rpc core module bean configuration
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Configuration
@Import(value = {
        ClusterEngine.class,
        RpcBaseCoreConfiguration.class
})
@ComponentScan(value = RpcConstants.RPC_CORE_STRATEGY_PACKAGE)
public class RpcCoreConfiguration {
}
