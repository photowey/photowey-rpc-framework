package com.photowey.rpc.client.invoker;

import com.photowey.rpc.core.exception.RpcException;
import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.response.RpcResponse;

/**
 * Invoker
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public interface Invoker<T> {

    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();

    /**
     * execute the invoke
     *
     * @param request the rpc Request
     * @return RpcResponse the rpc Response
     * @throws RpcException rpc 异常
     */
    RpcResponse invoke(RpcRequest request) throws RpcException;
}
