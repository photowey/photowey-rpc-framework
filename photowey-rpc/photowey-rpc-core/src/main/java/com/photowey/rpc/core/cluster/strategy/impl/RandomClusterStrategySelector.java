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

import com.photowey.rpc.core.enums.ClusterStrategyEnum;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * this selector using a random algorithm
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class RandomClusterStrategySelector extends AbstractClusterStrategySelector {

    @Override
    public ProviderServiceInfo doSelect(List<ProviderServiceInfo> routes) {
        int serviceCount = routes.size();
        int seed = RandomUtils.nextInt(0, serviceCount - 1);
        return routes.get(seed);
    }

    @Override
    public ClusterStrategyEnum strategy() {
        return ClusterStrategyEnum.RANDOM;
    }
}
