package com.photowey.rpc.client.proxy;

import com.photowey.rpc.client.proxy.factory.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * RpcFactoryBeanPostProcessor
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
public class RpcFactoryBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private RpcFactoryMapping rpcFactoryMapping;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        if (bean instanceof ProxyFactory) {
            this.rpcFactoryMapping.put(targetClass.getName(), (ProxyFactory) bean);
        }

        return bean;
    }
}
