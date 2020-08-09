/*
 * Copyright © 2020 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
