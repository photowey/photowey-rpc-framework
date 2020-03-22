package com.photowey.rpc.client.proxy;

import com.photowey.rpc.client.proxy.factory.ProxyFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RpcFactoryMapping
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
public class RpcFactoryMapping {

    private ConcurrentHashMap<String, ProxyFactory> proxyFactories = new ConcurrentHashMap<>();

    public void put(String key, ProxyFactory proxyFactory) {
        this.proxyFactories.put(key, proxyFactory);
    }

    public ProxyFactory get(String key) {
        return this.proxyFactories.get(key);
    }

    public Map<String, ProxyFactory> proxyFactories() {
        return proxyFactories;
    }
}
