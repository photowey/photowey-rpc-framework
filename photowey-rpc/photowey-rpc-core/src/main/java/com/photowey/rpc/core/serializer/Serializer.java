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
