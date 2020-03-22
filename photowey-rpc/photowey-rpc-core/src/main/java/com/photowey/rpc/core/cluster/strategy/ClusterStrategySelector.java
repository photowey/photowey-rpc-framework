package com.photowey.rpc.core.cluster.strategy;

import com.photowey.rpc.core.enums.ClusterStrategyEnum;
import com.photowey.rpc.core.exception.RpcServiceNotAvailableException;
import com.photowey.rpc.core.provider.ProviderServiceInfo;

import java.util.List;

/**
 * PollingClusterStrategySelector
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public interface ClusterStrategySelector {
    /**
     * select a target service for invoke
     *
     * @param routes the service list
     * @return the invoke target service
     */
    ProviderServiceInfo select(List<ProviderServiceInfo> routes) throws RpcServiceNotAvailableException;

    /**
     * get the support strategy
     *
     * @return {@link ClusterStrategyEnum}
     */
    ClusterStrategyEnum strategy();
}
