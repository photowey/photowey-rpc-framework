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
package com.photowey.rpc.client.request;

import com.photowey.rpc.core.response.RpcResponse;
import com.photowey.rpc.core.util.RpcUtils;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Promise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * the RpcRequest Pool
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Component
public class RpcRequestExecutor implements RequestExecutor {

    private final ConcurrentHashMap<String, Promise<RpcResponse>> requestPool = new ConcurrentHashMap<>();

    private static final int FETCH_RESPONSE_TIME_OUT_SECONDS = 10;

    @Autowired
    private RpcRequestHolder rpcRequestHolder;

    @Override
    public void submit(String sessionId, EventExecutor executor) {
        requestPool.put(sessionId, new DefaultPromise<>(executor));
    }

    @Override
    public RpcResponse fetch(String sessionId) throws Exception {
        Promise<RpcResponse> promise = requestPool.get(sessionId);
        if (RpcUtils.isEmpty(promise)) {
            return null;
        }
        RpcResponse rpcResponse = promise.get(FETCH_RESPONSE_TIME_OUT_SECONDS, TimeUnit.SECONDS);
        requestPool.remove(sessionId);

        this.rpcRequestHolder.destroyChannelHolder(sessionId);
        return rpcResponse;
    }

    @Override
    public void notify(String sessionId, RpcResponse rpcResponse) {
        Promise<RpcResponse> promise = requestPool.get(sessionId);
        if (RpcUtils.isNotEmpty(promise)) {
            promise.setSuccess(rpcResponse);
        }
    }
}
