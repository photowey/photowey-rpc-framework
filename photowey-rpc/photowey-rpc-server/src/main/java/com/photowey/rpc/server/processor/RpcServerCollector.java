package com.photowey.rpc.server.processor;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * RpcServerCollector
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
public class RpcServerCollector {

    public Map<String, Object> rpcServers = new HashMap<>();

    public void put(String key, Object rpcServer) {
        this.rpcServers.put(key, rpcServer);
    }

    public <T> T get(String key) {
        return (T) this.rpcServers.get(key);
    }

    public Map<String, Object> rpcServers() {
        return rpcServers;
    }
}
