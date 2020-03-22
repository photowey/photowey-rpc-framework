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
