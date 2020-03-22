package com.photowey.rpc.core.cluster.strategy.impl;

import org.junit.Before;
import org.junit.Test;

/**
 * WeightPollingClusterStrategySelectorTest
 *
 * @author WcJun
 * @date 2020/02/23
 */
public class WeightPollingClusterStrategySelectorTest extends AbstractClusterStrategySelectorTest {

    @Before
    public void init() {
        super.init("weightPolling");
    }

    @Test
    public void select() {
        for (int i = 0; i < 50; i++) {
            super.select();
        }
    } // the result ↓

    /**
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[1]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[2]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[2]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[2]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[3]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[3]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[4]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[4]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[4]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[4]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[5]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[6]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[6]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[6]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[6]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[6]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[7]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[7]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[7]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[7]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[7]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[7]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[7]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[0]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[1]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[2]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[2]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[2]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[3]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[3]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[4]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[4]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[4]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[4]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[5]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[6]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[6]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[6]
     * the [WeightPollingClusterStrategySelector] selected service ip is:[6]
     */
}