package com.photowey.rpc.core.context;

import java.util.Map;

/**
 * RpcContext
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public interface RpcContext extends Context {

    String HEADER_CONTAINER_NAME = "header-container";
    String RPC_REQUEST_ID = "request-id";

    <T> void addHeader(String key, T value);

    <T> T getHeader(String key);

    <T> Map<String, T> getHeaders();
}
