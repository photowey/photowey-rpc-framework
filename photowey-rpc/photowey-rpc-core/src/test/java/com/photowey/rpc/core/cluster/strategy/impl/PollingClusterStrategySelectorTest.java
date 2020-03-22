package com.photowey.rpc.core.cluster.strategy.impl;

import org.junit.Before;
import org.junit.Test;

/**
 * PollingClusterStrategySelectorTest
 *
 * @author WcJun
 * @date 2020/02/23
 */
public class PollingClusterStrategySelectorTest extends AbstractClusterStrategySelectorTest {

    @Before
    public void init() {
        super.init("polling");
    }

    /**
     * the [PollingClusterStrategySelector] selected service ip is:[0]
     * the [PollingClusterStrategySelector] selected service ip is:[1]
     * the [PollingClusterStrategySelector] selected service ip is:[2]
     * the [PollingClusterStrategySelector] selected service ip is:[3]
     * the [PollingClusterStrategySelector] selected service ip is:[4]
     * the [PollingClusterStrategySelector] selected service ip is:[5]
     * the [PollingClusterStrategySelector] selected service ip is:[6]
     * the [PollingClusterStrategySelector] selected service ip is:[7]
     * the [PollingClusterStrategySelector] selected service ip is:[0]
     * the [PollingClusterStrategySelector] selected service ip is:[1]
     */
    @Test
    public void select() {
        for (int i = 0; i < 10; i++) {
            super.select();
        }
    }
}
