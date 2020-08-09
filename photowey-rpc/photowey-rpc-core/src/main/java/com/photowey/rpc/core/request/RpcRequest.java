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
package com.photowey.rpc.core.request;

import com.photowey.rpc.core.context.RpcContext;

import java.io.Serializable;
import java.net.URL;

/**
 * RpcRequest
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public interface RpcRequest extends Serializable {

    /**
     * get the current request  session Id
     *
     * @return
     */
    String getSessionId();

    /**
     * get the remote class name
     *
     * @return the remote class name
     */
    String getClassName();

    /**
     * get the remote execute method name
     *
     * @return the remote execute method name
     */
    String getMethodName();

    /**
     * get the Types of method parameters
     *
     * @return the Types of method parameters
     */
    Class<?>[] getParameterTypes();

    /**
     * get the method parameters
     *
     * @return the method parameters
     */
    Object[] getParameters();

    /**
     * get the remote dubbo
     *
     * @return the remote dubbo
     */
    URL getUrl();

    /**
     * set the remote dubbo
     *
     * @param url the remote dubbo
     */
    void setUrl(URL url);

    /**
     * get the rpc request context
     *
     * @return RpcContext
     * @author WcJun
     * @date 2020/02/21
     */
    RpcContext getRpcContext();
}
