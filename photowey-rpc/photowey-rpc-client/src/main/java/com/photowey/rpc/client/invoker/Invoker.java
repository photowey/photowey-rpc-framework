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
package com.photowey.rpc.client.invoker;

import com.photowey.rpc.core.exception.RpcException;
import com.photowey.rpc.core.request.RpcRequest;
import com.photowey.rpc.core.response.RpcResponse;

/**
 * Invoker
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public interface Invoker<T> {

    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();

    /**
     * execute the invoke
     *
     * @param request the rpc Request
     * @return RpcResponse the rpc Response
     * @throws RpcException rpc 异常
     */
    RpcResponse invoke(RpcRequest request) throws RpcException;
}
