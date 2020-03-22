package com.photowey.rpc.core.serializer;

import com.photowey.rpc.core.context.DefaultRpcContext;
import com.photowey.rpc.core.context.RpcContext;
import com.photowey.rpc.core.request.DefaultRpcRequest;
import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.util.RpcUtils;
import com.photowey.rpc.core.util.SystemOutUtils;
import org.junit.Test;

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
