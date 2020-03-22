package com.photowey.rpc.core.context;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Context
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public interface Context {

    <T> void setAttribute(String key, T value);

    <T> T getAttribute(String key);

    <T> T getAttribute(String key, Supplier<T> supplier);

    <T> Map<String, T> getContext();
}
