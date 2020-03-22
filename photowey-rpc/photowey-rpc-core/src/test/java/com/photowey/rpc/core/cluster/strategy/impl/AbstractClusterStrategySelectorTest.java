package com.photowey.rpc.core.cluster.strategy.impl;

import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.RandomUtils;
import com.photowey.rpc.core.util.SystemOutUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractClusterStrategySelector
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public abstract class AbstractClusterStrategySelectorTest {

    protected com.photowey.rpc.core.cluster.strategy.ClusterStrategySelector clusterStrategySelector = null;
    List<ProviderServiceInfo> routes = new ArrayList<>();

    protected void init(String target) {
        this.clusterStrategySelector = this.populateStrategySelector(target);
        this.routes = this.populateRoutes();
    }

    protected void select() {
        ProviderServiceInfo select = this.clusterStrategySelector.select(this.routes);
        SystemOutUtils.println("the [{}] selected service ip is:[{}]", this.clusterStrategySelector.getClass().getSimpleName(), select.getServerIp());
    }

    protected com.photowey.rpc.core.cluster.strategy.ClusterStrategySelector populateStrategySelector(String target) {
        if ("random".equals(target)) {
            return new RandomClusterStrategySelector();
        } else if ("polling".equals(target)) {
            return new PollingClusterStrategySelector();
        } else if ("hash".equals(target)) {
            return new HashClusterStrategySelector();
        } else if ("weightPolling".equals(target)) {
            return new WeightPollingClusterStrategySelector();
        } else if ("weightRandom".equals(target)) {
            return new WeightRandomClusterStrategySelector();
        }

        return new RandomClusterStrategySelector();
    }

    protected List<ProviderServiceInfo> populateRoutes() {
        List<ProviderServiceInfo> routes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ProviderServiceInfo providerServiceInfo = ProviderServiceInfo.builder().serverIp(String.valueOf(i)).weight(RandomUtils.nextInt(0, 8)).build();
            routes.add(providerServiceInfo);
        }

        return routes;
    }
}
