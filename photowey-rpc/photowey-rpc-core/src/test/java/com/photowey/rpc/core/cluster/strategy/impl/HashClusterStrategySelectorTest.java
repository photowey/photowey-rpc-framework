package com.photowey.rpc.core.cluster.strategy.impl;

import org.junit.Before;
import org.junit.Test;

/**
 * HashClusterStrategySelectorTest
 *
 * @author WcJun
 * @date 2020/02/23
 */
public class HashClusterStrategySelectorTest extends AbstractClusterStrategySelectorTest {

    @Before
    public void init() {
        super.init("hash");
    }

    @Test
    public void select() {
        // 1.the [HashClusterStrategySelector] selected service ip is:[2]
        // 2.the [HashClusterStrategySelector] selected service ip is:[2]
        // ...
        // n.the [HashClusterStrategySelector] selected service ip is:[2]
        super.select();
    }
}