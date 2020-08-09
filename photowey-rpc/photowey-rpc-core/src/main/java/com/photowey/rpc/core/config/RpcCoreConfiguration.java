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
package com.photowey.rpc.core.config;

import com.photowey.rpc.core.cluster.ClusterEngine;
import com.photowey.rpc.core.util.RpcConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * the rpc core module bean configuration
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Configuration
@Import(value = {
        ClusterEngine.class,
        RpcBaseCoreConfiguration.class
})
@ComponentScan(value = RpcConstants.RPC_CORE_STRATEGY_PACKAGE)
public class RpcCoreConfiguration {
}
