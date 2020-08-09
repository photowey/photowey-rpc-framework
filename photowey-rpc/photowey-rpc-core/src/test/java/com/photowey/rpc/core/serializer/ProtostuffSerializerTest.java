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
package com.photowey.rpc.core.serializer;

import com.photowey.rpc.core.context.DefaultRpcContext;
import com.photowey.rpc.core.context.RpcContext;
import com.photowey.rpc.core.request.DefaultRpcRequest;
import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.util.RpcUtils;
import com.photowey.rpc.core.util.SystemOutUtils;

/**
 * ProtostuffSerializerTest
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public class ProtostuffSerializerTest {

    //    @Test
    public void testSerialize() {

        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();

        RpcContext rpcContext = this.populateRpcContext();

        String sessionId = RpcUtils.uuid();
        RpcRequest defaultRpcRequest = this.populateRpcRequest(rpcContext, sessionId);

        byte[] targets = protostuffSerializer.serialize(defaultRpcRequest);
        DefaultRpcRequest deserializeRpcRequest = protostuffSerializer.deserialize(targets, DefaultRpcRequest.class);

        // --- >>> deserialize, the  sessionId is:[72D3A47C36164EEBA079DDBCC3EFE782],and equals:[true]
        SystemOutUtils.println("--- >>> deserialize, the  sessionId is:[%s],and equals:[%b]",
                deserializeRpcRequest.getSessionId(),
                deserializeRpcRequest.getSessionId().equals(sessionId)
        );
    }

    private RpcRequest populateRpcRequest(RpcContext rpcContext, String sessionId) {
        return DefaultRpcRequest.builder()
                .sessionId(sessionId)
                .className("com.photowey.rpc.core.serializer.ProtostuffSerializer")
                .methodName("com.photowey.rpc.core.serializer.ProtostuffSerializer.serialize")
                .parameterTypes(new Class<?>[]{RpcContext.class})
                .parameters(null)
                .rpcContext(rpcContext)
                .build();
    }

    private RpcContext populateRpcContext() {
        RpcContext rpcContext = new DefaultRpcContext();
        rpcContext.setAttribute("photowey", "photowey");
        rpcContext.addHeader("ff", "939");
        return rpcContext;
    }
}
