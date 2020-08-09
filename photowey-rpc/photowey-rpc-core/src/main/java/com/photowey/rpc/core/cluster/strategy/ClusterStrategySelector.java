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
package com.photowey.rpc.core.cluster.strategy;

import com.photowey.rpc.core.enums.ClusterStrategyEnum;
import com.photowey.rpc.core.exception.RpcServiceNotAvailableException;
import com.photowey.rpc.core.provider.ProviderServiceInfo;

import java.util.List;

/**
 * PollingClusterStrategySelector
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public interface ClusterStrategySelector {
    /**
     * select a target service for invoke
     *
     * @param routes the service list
     * @return the invoke target service
     */
    ProviderServiceInfo select(List<ProviderServiceInfo> routes) throws RpcServiceNotAvailableException;

    /**
     * get the support strategy
     *
     * @return {@link ClusterStrategyEnum}
     */
    ClusterStrategyEnum strategy();
}
