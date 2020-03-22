package com.photowey.rpc.core.response;

/**
 * RpcResponse
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public interface RpcResponse {

    String getSessionId();

    Integer getRpcStatus();

    Object getRpcResult();

    String getRpcMessage();

    void setSessionId(String sessionId);

    void setRpcResult(Object rpcResult);

    void setRpcMessage(String rpcMessage);

    boolean isException();
}
