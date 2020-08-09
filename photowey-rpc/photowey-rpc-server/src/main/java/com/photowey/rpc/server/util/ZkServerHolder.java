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
package com.photowey.rpc.server.util;

import com.photowey.rpc.core.util.RpcConstants;
import com.photowey.rpc.server.properties.RpcServerProperties;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ZkServerHolder
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
public class ZkServerHolder {

    private static final Logger log = LoggerFactory.getLogger(ZkServerHolder.class);

    @Autowired
    private ZkClient zkClient;
    @Autowired
    private RpcServerProperties rpcServerProperties;

    public String createRootNode() {
        String root = this.getRoot();
        boolean exists = this.zkClient.exists(root);
        if (!exists) {
            String pathInner = root.replaceAll("^/", "").replaceAll("/$", "");
            if (pathInner.contains(RpcConstants.SEPARATOR)) {
                this.zkClient.createPersistent(root, true);
            } else {
                this.zkClient.createPersistent(root, false);
            }
        }

        return root;
    }

    public String createPersistentNode(String service) {
        String root = this.getRoot();
        String servicePath = root + RpcConstants.SEPARATOR + service;
        boolean exists = this.zkClient.exists(servicePath);
        if (!exists) {
            log.info("creat the service :[{}], root node:[{}]", service, servicePath);
            this.zkClient.createPersistent(servicePath);
        }

        return servicePath;
    }

    public void createNode(String servicePath, String instance) {
        String instancePath = servicePath + RpcConstants.SEPARATOR + instance;
        boolean exists = this.zkClient.exists(instancePath);
        if (!exists) {
            log.info("creat the instance node:[{}]", instancePath);
            this.zkClient.createEphemeral(instancePath);
        }
    }

    public String getRoot() {
        String root = this.rpcServerProperties.getZk().getRoot();
        return root;
    }
}
