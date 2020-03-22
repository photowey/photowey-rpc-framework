package com.photowey.rpc.core.cluster.strategy.impl;

import com.photowey.rpc.core.enums.ClusterStrategyEnum;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * this selector using a weight-random algorithm
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class WeightRandomClusterStrategySelector extends AbstractClusterStrategySelector {

    @Override
    public ProviderServiceInfo doSelect(List<ProviderServiceInfo> routes) {
        List<ProviderServiceInfo> reset = super.resetProviderServiceListByWeight(routes);
        int  weightCount = reset.size();
        int seed = RandomUtils.nextInt(0, weightCount - 1);

        return reset.get(seed);
    }

    @Override
    public ClusterStrategyEnum strategy() {
        return ClusterStrategyEnum.WEIGHT_RANDOM;
    }
}
