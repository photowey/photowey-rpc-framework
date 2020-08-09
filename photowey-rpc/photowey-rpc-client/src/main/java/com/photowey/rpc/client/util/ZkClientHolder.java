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
package com.photowey.rpc.client.util;

import com.google.common.collect.Lists;
import com.photowey.rpc.client.cache.ServiceRouteCache;
import com.photowey.rpc.client.parser.ServiceInfoParser;
import com.photowey.rpc.client.properties.RpcClientProperties;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.RpcConstants;
import com.photowey.rpc.core.util.RpcUtils;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * the Zk client holder
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Component
public class ZkClientHolder {

    private static final Logger log = LoggerFactory.getLogger(ZkClientHolder.class);

    @Autowired
    private RpcClientProperties rpcClientProperties;
    @Autowired
    private ZkClient zkClient;
    @Autowired
    private ServiceRouteCache serviceRouteCache;
    @Autowired
    private ServiceInfoParser serviceInfoParser;

    public void subscribe(String serviceName) {
        String root = this.rpcClientProperties.getClient().getZookeeper().getRoot();
        String parentPath = root + RpcConstants.SEPARATOR + serviceName;
        this.zkClient.subscribeChildChanges(parentPath, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChildren) throws Exception {
                if (RpcUtils.isNotEmpty(currentChildren)) {
                    List<ProviderServiceInfo> providerServices = parseProviderService(currentChildren);
                    serviceRouteCache.updateCache(serviceName, providerServices);
                }
            }
        });
    }

    public List<ProviderServiceInfo> getServiceInfos(String serviceName) {
        String root = this.rpcClientProperties.getClient().getZookeeper().getRoot();
        String servicePath = root + RpcConstants.SEPARATOR + serviceName;
        log.info("pull the service info from zk,path:[{}]", servicePath);
        List<String> children = this.zkClient.getChildren(servicePath);
        List<ProviderServiceInfo> providerServices = this.parseProviderService(children);

        return providerServices;
    }

    private List<ProviderServiceInfo> parseProviderService(List<String> currentChildren) {
        if (RpcUtils.isEmpty(currentChildren)) {
            return Lists.newArrayListWithCapacity(0);
        }
        // me#192.168.0.11:8080:9527@v1.0.0@30@3@com.photowey.rpc.hello.service.HelloService
        List<ProviderServiceInfo> providerServices = currentChildren
                .stream()
                .map(registerInfo -> this.serviceInfoParser.doParse(registerInfo))
                .collect(Collectors.toList());
        return providerServices;
    }
}
