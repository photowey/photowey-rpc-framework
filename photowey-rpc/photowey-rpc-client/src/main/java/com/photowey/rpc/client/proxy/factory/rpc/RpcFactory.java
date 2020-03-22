package com.photowey.rpc.client.proxy.factory.rpc;

import com.photowey.rpc.core.exception.RpcException;

/**
 * the Rpc client RpcFactory
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public interface RpcFactory {

    <T> T createProxy(String targetProxy, Class<T>[] interfaces) throws RpcException;
}
