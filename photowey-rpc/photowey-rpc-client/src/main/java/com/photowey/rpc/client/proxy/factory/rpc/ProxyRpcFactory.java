package com.photowey.rpc.client.proxy.factory.rpc;

import com.photowey.rpc.client.proxy.RpcFactoryMapping;
import com.photowey.rpc.client.proxy.factory.ProxyFactory;
import com.photowey.rpc.core.exception.RpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * ProxyRpcFactory
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class ProxyRpcFactory implements RpcFactory {

    @Autowired
    private RpcFactoryMapping rpcFactoryMapping;

    @Override
    public <T> T createProxy(String targetProxy, Class<T>[] interfaces) throws RpcException {
        // ServiceLoader<ProxyFactory> proxyFactories = ServiceLoader.load(ProxyFactory.class);
        Collection<ProxyFactory> proxyFactories = this.rpcFactoryMapping.proxyFactories().values();
        for (ProxyFactory proxyFactory : proxyFactories) {
            if (proxyFactory.supports(targetProxy)) {
                return proxyFactory.buildProxy(targetProxy, interfaces, proxyFactory.getInvoker(interfaces[0], null, null));
            }
        }

        return null;
    }
}
