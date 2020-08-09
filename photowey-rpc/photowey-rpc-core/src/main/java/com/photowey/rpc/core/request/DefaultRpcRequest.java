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

import com.photowey.rpc.core.context.DefaultRpcContext;
import com.photowey.rpc.core.context.RpcContext;
import com.photowey.rpc.core.util.RpcUtils;

import java.lang.reflect.Method;
import java.net.URL;

/**
 * DefaultRpcRequest
 *
 * @author WcJun
 * @date 2020/02/21
 */
public class DefaultRpcRequest implements RpcRequest {

    private static final long serialVersionUID = 4371392889158208537L;

    /**
     * the current request session Id
     */
    private String sessionId;
    /**
     * the remote class name
     */
    private String className;
    /**
     * the remote execute method name
     */
    private String methodName;
    /**
     * the Types of method parameters
     */
    private Class<?>[] parameterTypes;
    /**
     * the method parameters
     */
    private Object[] parameters;
    /**
     * get the remote dubbo
     *
     * @return the remote dubbo
     */
    private URL url;

    /**
     * the request context
     */
    private RpcContext rpcContext;


    public DefaultRpcRequest() {
    }

    public DefaultRpcRequest(Method method, Object[] arguments) {
        this(method.getDeclaringClass().getName(),
                method.getName(), method.getParameterTypes(),
                arguments
        );
    }

    public DefaultRpcRequest(String className, String methodName,
                             Class<?>[] parameterTypes, Object[] parameters) {
        String uuid = RpcUtils.uuid();
        this.sessionId = uuid;
        this.className = className;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
        RpcContext rpcContext = new DefaultRpcContext();
        rpcContext.setAttribute(RpcContext.RPC_REQUEST_ID, uuid);

        this.rpcContext = rpcContext;
    }

    public DefaultRpcRequest(String sessionId,
                             String className, String methodName,
                             Class<?>[] parameterTypes, Object[] parameters,
                             RpcContext rpcContext) {
        this.sessionId = sessionId;
        this.className = className;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
        this.rpcContext = rpcContext;
    }

    @Override
    public RpcContext getRpcContext() {
        return this.rpcContext;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    @Override
    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public void setUrl(URL url) {
        this.url = url;
    }

    public void setRpcContext(RpcContext rpcContext) {
        this.rpcContext = rpcContext;
    }

    public static DefaultRpcRequest.DefaultRpcRequestBuilder builder() {
        return new DefaultRpcRequest.DefaultRpcRequestBuilder();
    }

    public static class DefaultRpcRequestBuilder {
        private String sessionId;
        private String className;
        private String methodName;
        private Class<?>[] parameterTypes;
        private Object[] parameters;
        private RpcContext rpcContext;

        DefaultRpcRequestBuilder() {
        }

        public DefaultRpcRequest.DefaultRpcRequestBuilder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public DefaultRpcRequest.DefaultRpcRequestBuilder className(String className) {
            this.className = className;
            return this;
        }

        public DefaultRpcRequest.DefaultRpcRequestBuilder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public DefaultRpcRequest.DefaultRpcRequestBuilder parameterTypes(Class<?>[] parameterTypes) {
            this.parameterTypes = parameterTypes;
            return this;
        }

        public DefaultRpcRequest.DefaultRpcRequestBuilder parameters(Object[] parameters) {
            this.parameters = parameters;
            return this;
        }

        public DefaultRpcRequest.DefaultRpcRequestBuilder rpcContext(RpcContext rpcContext) {
            this.rpcContext = rpcContext;
            return this;
        }

        public DefaultRpcRequest build() {
            return new DefaultRpcRequest(this.sessionId, this.className, this.methodName, this.parameterTypes, this.parameters, this.rpcContext);
        }
    }
}
