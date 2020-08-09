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
