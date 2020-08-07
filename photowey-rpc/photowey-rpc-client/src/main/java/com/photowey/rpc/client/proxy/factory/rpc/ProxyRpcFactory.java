package com.photowey.rpc.client.proxy.factory.rpc;

import com.photowey.rpc.client.proxy.factory.CglibProxyFactory;
import com.photowey.rpc.client.proxy.factory.ProxyFactory;
import com.photowey.rpc.core.exception.RpcException;
import com.photowey.rpc.core.util.RpcUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ServiceLoader;

/**
 * ProxyRpcFactory
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class ProxyRpcFactory implements RpcFactory, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public <T> T createProxy(String targetProxy, Class<T>[] interfaces) throws RpcException {
        ServiceLoader<ProxyFactory> proxyFactories = ServiceLoader.load(ProxyFactory.class);
        List<ProxyFactory> targets = RpcUtils.toList(proxyFactories.iterator());
        if (RpcUtils.isNotEmpty(targets)) {
            for (ProxyFactory proxyFactory : targets) {
                if (proxyFactory.supports(targetProxy)) {
                    return proxyFactory.buildProxy(targetProxy, interfaces, proxyFactory.getInvoker(interfaces[0], null, null));
                }
            }
        } else {
            CglibProxyFactory proxyFactory = this.getDefaultProxyFactory();
            return proxyFactory.buildProxy(targetProxy, interfaces, proxyFactory.getInvoker(interfaces[0], null, null));
        }

        return null;
    }

    private CglibProxyFactory getDefaultProxyFactory() {
        return this.beanFactory.getBean(CglibProxyFactory.class);
    }
}
