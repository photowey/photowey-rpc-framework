package com.photowey.rpc.core.cluster.strategy.impl;

import com.photowey.rpc.core.enums.ClusterStrategyEnum;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * this selector using a random algorithm
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class RandomClusterStrategySelector extends AbstractClusterStrategySelector {

    @Override
    public ProviderServiceInfo doSelect(List<ProviderServiceInfo> routes) {
        int serviceCount = routes.size();
        int seed = RandomUtils.nextInt(0, serviceCount - 1);
        return routes.get(seed);
    }

    @Override
    public ClusterStrategyEnum strategy() {
        return ClusterStrategyEnum.RANDOM;
    }
}
