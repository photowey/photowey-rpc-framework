package com.photowey.rpc.client.proxy.factory;

import com.photowey.rpc.client.invoker.Invoker;
import com.photowey.rpc.core.exception.RpcException;

import java.net.URL;

/**
 * ProxyFactory
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public interface ProxyFactory {

    boolean supports(String targetProxy);

    <T> T buildProxy(String targetProxy, Class<T>[] interfaces, Invoker proxyInvoker) throws RpcException;

    <T> Invoker getInvoker(Class<T> proxyType, Object proxyObject, URL url);
}
