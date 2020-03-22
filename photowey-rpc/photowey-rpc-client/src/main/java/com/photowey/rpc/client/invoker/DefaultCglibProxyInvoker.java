package com.photowey.rpc.client.invoker;

import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.response.RpcResponse;

/**
 * DefaultCglibProxyInvoker
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public class DefaultCglibProxyInvoker extends AbstractProxyInvoker {

    public DefaultCglibProxyInvoker(Class proxyType) {
        super(proxyType);
    }

    @Override
    protected RpcResponse doInvoke(RpcRequest request) throws Throwable {
        return super.handleInvoke(request);
    }
}
