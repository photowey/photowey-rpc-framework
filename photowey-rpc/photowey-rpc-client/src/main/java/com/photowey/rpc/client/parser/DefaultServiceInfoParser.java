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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * the default serviceInfo parser
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Component
public class DefaultServiceInfoParser implements ServiceInfoParser {

    private static final Logger log = LoggerFactory.getLogger(DefaultServiceInfoParser.class);

    @Override
    public ProviderServiceInfo doParse(String registryInfo) {

        // me#192.168.0.11:8080:9527@1.0.0@30@3@com.photowey.rpc.hello.service.HelloService
        String protocol = registryInfo.substring(0, registryInfo.lastIndexOf("#"));

        registryInfo = registryInfo.replaceAll(protocol + "#", "");
        String serverIp = registryInfo.substring(0, registryInfo.indexOf(":"));

        registryInfo = registryInfo.replaceAll(serverIp + ":", "");
        String serverPort = registryInfo.substring(0, registryInfo.indexOf(":"));

        registryInfo = registryInfo.replaceAll(serverPort + ":", "");
        String networkPort = registryInfo.substring(0, registryInfo.indexOf("@"));

        registryInfo = registryInfo.replaceAll(networkPort + "@", "");
        String version = registryInfo.substring(0, registryInfo.indexOf("@"));

        registryInfo = registryInfo.replaceAll(version + "@", "");
        String timeout = registryInfo.substring(0, registryInfo.indexOf("@"));

        registryInfo = registryInfo.replaceAll(timeout + "@", "");
        String weight = registryInfo.substring(0, registryInfo.indexOf("@"));

        registryInfo = registryInfo.replaceAll(weight + "@", "");
        String appName = registryInfo;
        ProviderServiceInfo providerServiceInfo = ProviderServiceInfo.builder()
                .protocol(protocol)
                .serverIp(serverIp)
                .serverPort(Integer.valueOf(serverPort))
                .networkPort(Integer.valueOf(networkPort))
                .timeout(Integer.valueOf(timeout))
                .weight(Integer.valueOf(weight))
                .version(version)
                .appName(appName)
                .build();

        return providerServiceInfo;
    }
}
