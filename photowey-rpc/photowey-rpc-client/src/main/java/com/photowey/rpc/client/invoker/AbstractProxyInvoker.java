package com.photowey.rpc.client.invoker;

import com.photowey.rpc.client.request.RequestExecutor;
import com.photowey.rpc.client.request.RpcRequestHolder;
import com.photowey.rpc.core.exception.RpcException;
import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.response.DefaultRpcResponse;
import com.photowey.rpc.core.response.RpcResponse;
import com.photowey.rpc.core.util.RpcUtils;
import com.photowey.rpc.core.util.SystemOutUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * AbstractProxyInvoker
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public abstract class AbstractProxyInvoker<T> implements Invoker<T> {

    private static final Logger log = LoggerFactory.getLogger(AbstractProxyInvoker.class);

    private Class<T> proxyType;

    public AbstractProxyInvoker(Class<T> proxyType) {
        this.proxyType = proxyType;
    }

    @Override
    public Class<T> getInterface() {
        return this.proxyType;
    }

    @Override
    public RpcResponse invoke(RpcRequest request) throws RpcException {
        String sessionId = request.getSessionId();
        String methodName = request.getMethodName();
        String className = request.getClassName();
        try {
            Object response = this.doInvoke(request);
            return (RpcResponse) response;
        } catch (InvocationTargetException e) {
            return DefaultRpcResponse.failure(sessionId, e.getTargetException().getMessage());
        } catch (Throwable e) {
            String message = SystemOutUtils.format(
                    "Failed to invoke remote proxy method [{}] to remote service:[{}], message:[{}] ",
                    methodName, className, e.getMessage()
            );
            throw new RpcException(message, e);
        }
    }

    /**
     * doInvoke
     *
     * @param request the rpc request object
     * @return {@link RpcResponse}
     * @throws Throwable
     */
    protected abstract Object doInvoke(RpcRequest request) throws Throwable;

    protected RpcResponse handleInvoke(RpcRequest request) throws Throwable {
        String sessionId = request.getSessionId();
        log.info("handle the request,the sessionId:[{}]", sessionId);
        RpcRequestHolder rpcRequestHolder = SpringContextHolder.getBean(RpcRequestHolder.class);
        rpcRequestHolder.doRequest(request);
        RequestExecutor requestExecutor = SpringContextHolder.getBean(RequestExecutor.class);
        RpcResponse rpcResponse = requestExecutor.fetch(sessionId);
        if (RpcUtils.isEmpty(rpcResponse)) {
            return null;
        }

        if (rpcResponse.isException()) {
            throw new RpcException(rpcResponse.getRpcMessage());
        }

        return rpcResponse;
    }
}
