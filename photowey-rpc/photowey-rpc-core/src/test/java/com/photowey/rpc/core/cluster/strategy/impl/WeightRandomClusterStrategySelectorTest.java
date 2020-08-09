/*
 * Copyright © 2020 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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