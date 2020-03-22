package com.photowey.rpc.client.invoker;

import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.response.RpcResponse;

/**
 * DefaultJdkProxyInvoker
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public class DefaultJdkProxyInvoker extends AbstractProxyInvoker {

    public DefaultJdkProxyInvoker(Class proxyType) {
        super(proxyType);
    }

    @Override
    public RpcResponse doInvoke(RpcRequest request) throws Throwable {
        return super.handleInvoke(request);
    }
}
