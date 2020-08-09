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
package com.photowey.rpc.client.proxy;

import com.photowey.rpc.client.invoker.Invoker;

/**
 * Proxy
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public interface Proxy {

    /**
     * get Proxy
     *
     * @param interfaceClass the Interface class
     * @param proxyInvoker   the Proxy Invoker
     * @param <T>            T
     * @return the Proxy Object
     */
    <T> T getProxy(Class<T> interfaceClass, Invoker proxyInvoker);

    /**
     * get invoker from the proxyObject
     *
     * @param proxyObject the Proxy Object
     * @return Invoker
     */
    Invoker getInvoker(Object proxyObject);
}
