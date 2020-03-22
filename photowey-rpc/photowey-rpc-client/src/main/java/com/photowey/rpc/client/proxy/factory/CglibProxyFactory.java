package com.photowey.rpc.client.proxy.factory;

import com.photowey.rpc.client.invoker.DefaultCglibProxyInvoker;
import com.photowey.rpc.client.invoker.Invoker;
import com.photowey.rpc.client.proxy.handler.CglibInvokerInvocationHandler;
import com.photowey.rpc.core.exception.RpcException;
import net.sf.cglib.proxy.Enhancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * JdkProxyFactory
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class CglibProxyFactory implements ProxyFactory {

    private static final String TARGET_PROXY = "cglib";

    private static final Logger log = LoggerFactory.getLogger(CglibProxyFactory.class);

    public CglibProxyFactory() {
        /*try {
            if (null != CglibProxyFactory.CglibProxyInnerFactory.INSTANCE) {
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
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(interfaces[0]);
        enhancer.setCallback(new CglibInvokerInvocationHandler(proxyInvoker));
        return (T) enhancer.create();
    }

    @Override
    public <T> Invoker getInvoker(Class<T> proxyType, Object proxyObject, URL url) {
        return new DefaultCglibProxyInvoker(proxyType);
    }

    /*private static class CglibProxyInnerFactory {
        private static final CglibProxyFactory INSTANCE = new CglibProxyFactory();
    }

    public static CglibProxyFactory getInstance() {
        return CglibProxyFactory.CglibProxyInnerFactory.INSTANCE;
    }*/
}
