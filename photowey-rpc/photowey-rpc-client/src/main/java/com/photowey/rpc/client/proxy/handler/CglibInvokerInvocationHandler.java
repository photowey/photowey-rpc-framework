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
package com.photowey.rpc.client.proxy.handler;

import com.photowey.rpc.client.invoker.Invoker;
import com.photowey.rpc.core.request.DefaultRpcRequest;
import com.photowey.rpc.core.response.RpcResponse;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JdkInvokerInvocationHandler
 *
 * @author WcJun
 * @date 2020/02/22
 */
public class CglibInvokerInvocationHandler implements MethodInterceptor {

    public static final String TO_STRING = "toString";
    public static final String HASH_CODE = "hashCode";
    public static final String EQUALS = "equals";

    private final Invoker<?> proxyInvoker;

    public CglibInvokerInvocationHandler(Invoker<?> proxyInvoker) {
        this.proxyInvoker = proxyInvoker;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return this.doIntercept(proxy, method, objects);
    }

    public Object doIntercept(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this.proxyInvoker, args);
        }
        if (TO_STRING.equals(methodName) && parameterTypes.length == 0) {
            return this.proxyInvoker.toString();
        }
        if (HASH_CODE.equals(methodName) && parameterTypes.length == 0) {
            return this.proxyInvoker.hashCode();
        }
        if (EQUALS.equals(methodName) && parameterTypes.length == 1) {
            return this.proxyInvoker.equals(args[0]);
        }
        RpcResponse rpcResponse = this.proxyInvoker.invoke(new DefaultRpcRequest(method, args));
        return rpcResponse.getRpcResult();
    }
}
