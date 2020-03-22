package com.photowey.rpc.core.cluster.strategy.impl;

import org.junit.Before;
import org.junit.Test;

/**
 * WeightRandomClusterStrategySelectorTest
 *
 * @author WcJun
 * @date 2020/02/23
 */
public class WeightRandomClusterStrategySelectorTest extends AbstractClusterStrategySelectorTest {

    @Before
    public void init() {
        super.init("weightRandom");
    }

    @Test
    public void select() {
        // 1.the [WeightRandomClusterStrategySelector] selected service ip is:[5]
        // 2.the [WeightRandomClusterStrategySelector] selected service ip is:[4]
        // ...
        // n.the [WeightRandomClusterStrategySelector] selected service ip is:[4]
        super.select();
    }
}