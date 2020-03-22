package com.photowey.rpc.server.processor;

import com.photowey.rpc.annotation.server.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * RpcServerBeanPostProcessor
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Component
public class RpcServerBeanPostProcessor implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(RpcServerBeanPostProcessor.class);

    @Autowired
    private RpcServerCollector rpcServerCollector;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        if (targetClass.isAnnotationPresent(RpcServer.class)) {
            this.rpcServerCollector.put(targetClass.getName(), bean);
            log.info("find a rpc server:[{}]", targetClass.getName());
        }

        return bean;
    }
}
