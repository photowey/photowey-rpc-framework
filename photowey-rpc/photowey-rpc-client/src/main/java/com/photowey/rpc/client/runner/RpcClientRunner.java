package com.photowey.rpc.client.runner;

import com.photowey.rpc.client.pull.PullServiceExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RpcClientRunner
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
public class RpcClientRunner implements SmartInitializingSingleton {

    private static final Logger log = LoggerFactory.getLogger(RpcClientRunner.class);

    @Autowired
    private PullServiceExecutor pullServiceExecutor;

    @Override
    public void afterSingletonsInstantiated() {
        try {
            this.pullServiceExecutor.pullService();
        } catch (Exception e) {
            log.error("pull the service info from zk server exception", e);
        }
    }
}
