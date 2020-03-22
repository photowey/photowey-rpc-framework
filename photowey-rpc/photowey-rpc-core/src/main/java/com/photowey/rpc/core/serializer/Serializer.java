package com.photowey.rpc.core.serializer;

import com.dyuproject.protostuff.Schema;

/**
 * Serializer
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public interface Serializer {

    /**
     * serialize the Source Object
     *
     * @param source the target Object
     * @param <T>    T
     * @return T
     */
    <T> byte[] serialize(T source);

    /**
     * deserialize the Source Object
     *
     * @param source    the byte[] source
     * @param typeClass the Target Type
     * @param <T>       T
     * @return T
     */
    <T> T deserialize(byte[] source, Class<T> typeClass);

    /**
     * get the Target Type Schema
     *
     * @param targetClass the Target Type
     * @param <T>         T
     * @return Schema<T>
     * @see com.dyuproject.protostuff.Schema
     */
    <T> Schema<T> getClassSchema(Class<T> targetClass);
}
