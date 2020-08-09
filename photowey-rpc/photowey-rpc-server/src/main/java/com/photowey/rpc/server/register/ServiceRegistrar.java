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
package com.photowey.rpc.server.register;

import com.photowey.rpc.annotation.server.RpcServer;
import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.util.IpUtils;
import com.photowey.rpc.core.util.RpcUtils;
import com.photowey.rpc.core.util.SystemOutUtils;
import com.photowey.rpc.server.properties.RpcServerProperties;
import com.photowey.rpc.server.util.ZkServerHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ServiceRegistrar
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
public class ServiceRegistrar {

    private static final Logger log = LoggerFactory.getLogger(ServiceRegistrar.class);

    @Autowired
    private ZkServerHolder zkServerHolder;
    @Autowired
    private RpcServerProperties rpcServerProperties;

    public void register() {
        Map<String, Object> beanWithAnnotations =
                SpringContextHolder.getBeansWithAnnotation(RpcServer.class);
        if (null == beanWithAnnotations || beanWithAnnotations.size() == 0) {
            return;
        }
        this.zkServerHolder.createRootNode();
        for (Object bean : beanWithAnnotations.values()) {
            RpcServer rpcServer = bean.getClass().getAnnotation(RpcServer.class);
            String serviceName = rpcServer.value().getName();
            if (null == serviceName || serviceName.trim().length() == 0) {
                String simpleName = bean.getClass().getSimpleName();
                serviceName = RpcUtils.generateBeanName(simpleName, null);
            }
            this.registerServerInfo(serviceName, rpcServer);
        }
    }

    private void registerServerInfo(String serviceName, RpcServer rpcServer) {
        String servicePath = this.zkServerHolder.createPersistentNode(serviceName);
        String template = "me#%s:%d:%d@%s@30@3@%s";
        String version = rpcServer.version();
        String instancePath = SystemOutUtils.format(
                template, IpUtils.getLocalIP(),
                this.rpcServerProperties.getServer().getPort(),
                this.rpcServerProperties.getNetwork().getPort(),
                version,
                serviceName
        );
        //  me#192.168.0.11:8080@9527@v1.0.0@30@3@com.photowey.hello.service.HelloService
        this.zkServerHolder.createNode(servicePath, instancePath);

        log.info("register the service:[{}]:[{}]:[{}] into zookeeper successfully", serviceName, servicePath, instancePath, instancePath);
    }
}
