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
package com.photowey.rpc.client.parser;

import com.photowey.rpc.core.provider.ProviderServiceInfo;

/**
 * the Service info Parser
 * <p>
 * the service info:
 * me#192.168.0.11:8080:9527@v1.0.0@30@3@com.photowey.rpc.hello.service.HelloService
 * protocol: me
 * ip: 192.168.0.11
 * port: 8080
 * network-port: 9527
 * version: v1.0.0
 * timeout: 30
 * weight: 3
 * app-name: com.photowey.rpc.hello.service.HelloService
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public interface ServiceInfoParser {

    /**
     * parse the service register info to {@link ProviderServiceInfo} Object
     *
     * @param registryInfo the service register info
     * @return {@link ProviderServiceInfo}
     */
    ProviderServiceInfo doParse(String registryInfo);
}
