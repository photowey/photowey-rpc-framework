package com.photowey.rpc.core.cluster.strategy.impl;

import org.junit.Before;
import org.junit.Test;

/**
 * RandomClusterStrategySelectorTest
 *
 * @author WcJun
 * @date 2020/02/22
 */
public class RandomClusterStrategySelectorTest extends AbstractClusterStrategySelectorTest {

    @Before
    public void init() {
        super.init("random");
    }

    @Test
    public void select() {
        // the [RandomClusterStrategySelector] selected service ip is:[2]
        super.select();
    }
}
