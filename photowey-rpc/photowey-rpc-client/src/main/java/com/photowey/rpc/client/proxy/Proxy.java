package com.photowey.rpc.client.proxy;

import com.photowey.rpc.client.invoker.Invoker;

/**
 * Proxy
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public interface Proxy {

    /**
     * get Proxy
     *
     * @param interfaceClass the Interface class
     * @param proxyInvoker   the Proxy Invoker
     * @param <T>            T
     * @return the Proxy Object
     */
    <T> T getProxy(Class<T> interfaceClass, Invoker proxyInvoker);

    /**
     * get invoker from the proxyObject
     *
     * @param proxyObject the Proxy Object
     * @return Invoker
     */
    Invoker getInvoker(Object proxyObject);
}
