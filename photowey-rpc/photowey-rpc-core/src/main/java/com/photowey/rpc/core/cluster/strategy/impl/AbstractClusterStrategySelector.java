package com.photowey.rpc.core.cluster.strategy.impl;

import com.photowey.rpc.core.cluster.strategy.ClusterStrategySelector;
import com.photowey.rpc.core.enums.RpcStatus;
import com.photowey.rpc.core.exception.RpcServiceNotAvailableException;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.RpcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AbstractClusterStrategySelector
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public abstract class AbstractClusterStrategySelector implements ClusterStrategySelector {

    protected static final Logger log = LoggerFactory.getLogger(AbstractClusterStrategySelector.class);

    /**
     * select a target service for invoke
     *
     * @param routes the service list
     * @return the invoke target service
     */
    @Override
    public ProviderServiceInfo select(List<ProviderServiceInfo> routes) throws RpcServiceNotAvailableException {
        if (null == routes) {
            // the checkAvailable will be throw {@link RpcServiceNotAvailableException}
            // not NullPointerException
            routes = Collections.emptyList();
        }
        this.checkAvailable(routes);
        int serviceCount = routes.size();
        if (1 == serviceCount) {
            return routes.get(0);
        }
        return this.doSelect(routes);
    }

    /**
     * check the service list can be used
     *
     * @param routes the service list
     */
    protected void checkAvailable(List<ProviderServiceInfo> routes) {
        if (RpcUtils.isEmpty(routes)) {
            throw new RpcServiceNotAvailableException(RpcStatus.NO_AVAILABLE.toValue(), "in current service list, have not any service can be used!");
        }
    }

    /**
     * executing decision options based on different selector algorithms
     *
     * @param routes the service list
     * @return the invoke target service
     */
    public abstract ProviderServiceInfo doSelect(List<ProviderServiceInfo> routes);

    /**
     * reset the service list by itself weight
     *
     * @param routes the service list
     * @return the reset service list
     */
    protected List<ProviderServiceInfo> resetProviderServiceListByWeight(List<ProviderServiceInfo> routes) {
        List<ProviderServiceInfo> reset = new ArrayList<>();
        for (ProviderServiceInfo current : routes) {
            int weight = current.getWeight();
            for (int i = 0; i < weight; i++) {
                reset.add(current);
            }
        }

        return reset;
    }
}