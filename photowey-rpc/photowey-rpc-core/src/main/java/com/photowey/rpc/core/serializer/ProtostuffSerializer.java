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

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * the Protostuff Serializer
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
@Component
public class ProtostuffSerializer implements Serializer {

    private static final Logger log = LoggerFactory.getLogger(ProtostuffSerializer.class);

    private static Map<Class<?>, Schema<?>> CLASS_SCHEMA_CACHE = new ConcurrentHashMap<>();
    private static Objenesis OBJENESIS_STD = new ObjenesisStd(true);

    /**
     * serialize the Source Object
     *
     * @param source the target Object
     * @param <T>    T
     * @return T
     */
    @Override
    public <T> byte[] serialize(T source) {
        Class<T> typeClass = (Class<T>) source.getClass();
        LinkedBuffer linkedBuffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = this.getClassSchema(typeClass);
            return ProtobufIOUtil.toByteArray(source, schema, linkedBuffer);
        } catch (Exception e) {
            log.error("--- >>> serialize the target source:[{}] exception <<< ---", typeClass.getSimpleName(), e);
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            linkedBuffer.clear();
        }
    }

    /**
     * deserialize the Source Object
     *
     * @param source    the byte[] source
     * @param typeClass the Target Type
     * @param <T>       T
     * @return T
     */
    @Override
    public <T> T deserialize(byte[] source, Class<T> typeClass) {
        try {
            Schema<T> schema = getClassSchema(typeClass);
            T message = OBJENESIS_STD.newInstance(typeClass);
            ProtobufIOUtil.mergeFrom(source, message, schema);
            return message;
        } catch (Exception e) {
            log.error("--- >>> deserialize the target source:[{}] exception <<< ---", typeClass.getSimpleName(), e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    // ================================================================

    @Override
    public <T> Schema<T> getClassSchema(Class<T> targetClass) {
        Schema<T> classSchema = null;
        if (CLASS_SCHEMA_CACHE.containsKey(targetClass)) {
            classSchema = (Schema<T>) CLASS_SCHEMA_CACHE.get(targetClass);
        } else {
            classSchema = RuntimeSchema.getSchema(targetClass);
            if (classSchema != null) {
                CLASS_SCHEMA_CACHE.put(targetClass, classSchema);
            }
        }

        return classSchema;
    }
}
