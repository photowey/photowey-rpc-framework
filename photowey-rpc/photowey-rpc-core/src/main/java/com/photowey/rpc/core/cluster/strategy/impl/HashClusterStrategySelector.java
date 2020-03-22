package com.photowey.rpc.core.cluster.strategy.impl;

import com.photowey.rpc.core.enums.ClusterStrategyEnum;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.IpUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * this selector using a hash algorithm
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class HashClusterStrategySelector extends AbstractClusterStrategySelector {

    @Override
    public ProviderServiceInfo doSelect(List<ProviderServiceInfo> routes) {
        String localIP = IpUtils.getLocalIP();
        int ipHash = localIP.hashCode();
        log.info("find this server ip is:[{}],and hashCode is:[{}],the routes count is:[{}]", localIP, ipHash, routes.size());
        int serviceCount = routes.size();

        return routes.get(ipHash % serviceCount);
    }

    @Override
    public ClusterStrategyEnum strategy() {
        return ClusterStrategyEnum.HASH;
    }
}
