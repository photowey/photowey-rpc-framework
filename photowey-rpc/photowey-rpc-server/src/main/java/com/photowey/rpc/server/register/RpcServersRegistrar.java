package com.photowey.rpc.server.register;

import com.photowey.rpc.server.runner.RpcServerRunner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * RpcServersRegistrar
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
public class RpcServersRegistrar implements SmartInitializingSingleton, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterSingletonsInstantiated() {
        RpcServerRunner rpcServerRunner = this.beanFactory.getBean(RpcServerRunner.class);
        rpcServerRunner.run();
    }
}
