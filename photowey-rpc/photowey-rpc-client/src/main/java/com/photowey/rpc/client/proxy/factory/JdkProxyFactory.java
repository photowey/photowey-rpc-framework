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
package com.photowey.rpc.client.proxy.factory;

import com.photowey.rpc.client.invoker.DefaultJdkProxyInvoker;
import com.photowey.rpc.client.invoker.Invoker;
import com.photowey.rpc.client.proxy.handler.JdkInvokerInvocationHandler;
import com.photowey.rpc.core.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.net.URL;

/**
 * JdkProxyFactory
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class JdkProxyFactory implements ProxyFactory {

    private static final String TARGET_PROXY = "jdk";

    private static final Logger log = LoggerFactory.getLogger(JdkProxyFactory.class);

    public JdkProxyFactory() {
        /*try {
            if (null != JdkProxyFactory.JdkProxyInnerFactory.INSTANCE) {
                throw new IllegalAccessException("the instance already exist,don't illegal use the reflect to create another one");
            }
        } catch (Exception e) {
            log.error("the Illegal create the instance", e);
        }*/
    }

    @Override
    public boolean supports(String targetProxy) {
        return TARGET_PROXY.equals(targetProxy);
    }

    @Override
    public <T> T buildProxy(String targetProxy, Class<T>[] interfaces, Invoker proxyInvoker) throws RpcException {
        return (T) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                interfaces,
                new JdkInvokerInvocationHandler(proxyInvoker)
        );
    }

    @Override
    public <T> Invoker getInvoker(Class<T> proxyType, Object proxyObject, URL url) {
        return new DefaultJdkProxyInvoker(proxyType);
    }

    /*private static class JdkProxyInnerFactory {
        private static final JdkProxyFactory INSTANCE = new JdkProxyFactory();
    }

    public static JdkProxyFactory getInstance() {
        return JdkProxyFactory.JdkProxyInnerFactory.INSTANCE;
    }*/
}
