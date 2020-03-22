package com.photowey.rpc.core.cluster.strategy.impl;

import com.photowey.rpc.core.enums.ClusterStrategyEnum;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * this selector using a weight-polling algorithm
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class WeightPollingClusterStrategySelector extends AbstractPollingClusterStrategySelector {

    @Override
    public  List<ProviderServiceInfo> doReset(List<ProviderServiceInfo> routes) {
        return super.resetProviderServiceListByWeight(routes);
    }

    @Override
    public ClusterStrategyEnum strategy() {
        return ClusterStrategyEnum.WEIGHT_POLLING;
    }
}
