package com.photowey.rpc.client.request;

import com.photowey.rpc.core.response.RpcResponse;
import io.netty.util.concurrent.EventExecutor;

/**
 * the request executor
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public interface RequestExecutor {

    void submit(String requestId, EventExecutor executor);

    RpcResponse fetch(String sessionId) throws Exception;

    void notify(String sessionId, RpcResponse response);
}
