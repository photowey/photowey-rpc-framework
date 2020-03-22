package com.photowey.rpc.core.cluster;

import com.photowey.rpc.core.cluster.strategy.ClusterStrategySelector;

/**
 * the Cluster mode
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public interface Cluster {

    /**
     * determine the strategy selector in cluster mode
     *
     * @param clusterStrategy the strategy
     * @return the target strategy selector
     */
    ClusterStrategySelector determineClusterStrategy(String clusterStrategy);
}
