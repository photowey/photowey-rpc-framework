package com.photowey.rpc.core.response;

import com.photowey.rpc.core.enums.RpcStatus;
import com.photowey.rpc.core.util.RpcUtils;

/**
 * the default RpcResponse
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public class DefaultRpcResponse implements RpcResponse {

    /**
     * the current request session Id
     */
    private String sessionId;
    /**
     * the current rpc request status
     * 1: success
     * 0: failure
     */
    private Integer rpcStatus;
    /**
     * the current rpc request response data
     */
    private Object rpcResult;
    /**
     * the current rpc request response message
     */
    private String rpcMessage;

    public DefaultRpcResponse() {
    }

    public DefaultRpcResponse(String sessionId, Integer rpcStatus, String rpcMessage) {
        this.sessionId = sessionId;
        this.rpcStatus = rpcStatus;
        this.rpcMessage = rpcMessage;
    }

    public DefaultRpcResponse(String sessionId, Integer rpcStatus, Object rpcResult, String rpcMessage) {
        this.sessionId = sessionId;
        this.rpcStatus = rpcStatus;
        this.rpcResult = rpcResult;
        this.rpcMessage = rpcMessage;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public Integer getRpcStatus() {
        return rpcStatus;
    }

    public void setRpcStatus(Integer rpcStatus) {
        this.rpcStatus = rpcStatus;
    }

    @Override
    public Object getRpcResult() {
        return rpcResult;
    }

    @Override
    public void setRpcResult(Object rpcResult) {
        this.rpcResult = rpcResult;
    }

    @Override
    public String getRpcMessage() {
        return rpcMessage;
    }

    @Override
    public void setRpcMessage(String rpcMessage) {
        this.rpcMessage = rpcMessage;
    }

    @Override
    public boolean isException() {
        return RpcUtils.isNotEmpty(this.getRpcMessage());
    }

    public static DefaultRpcResponse failure(String sessionId, String rpcMessage) {
        return new DefaultRpcResponse(sessionId, RpcStatus.FAILURE.toValue(), rpcMessage);
    }
}
