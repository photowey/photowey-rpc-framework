package com.photowey.rpc.client.register;

import com.photowey.rpc.client.proxy.factory.rpc.ProxyRpcFactory;
import com.photowey.rpc.client.proxy.factory.rpc.RpcFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * the RpcClient FactoryBean
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
class RpcClientFactoryBean<T>
        implements FactoryBean<T>, InitializingBean, ApplicationContextAware {

    private String service;
    private String version;
    private String contextId;
    private Class<?> type;
    /**
     * jdk or cglib
     */
    private String targetProxy;

    private ApplicationContext applicationContext;

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public T getObject() throws Exception {
        return this.getTarget();
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(this.service, "serviceName id must be set");
        Assert.hasText(this.version, "version must be set");
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    // ===================================================================

    private <T> T getTarget() {
        return this.createProxy();
    }

    private <T> T createProxy() {
        RpcFactory rpcFactory = this.applicationContext.getBean(ProxyRpcFactory.class);
        return (T) rpcFactory.createProxy(this.targetProxy, new Class[]{this.type});
    }
    // ===================================================================

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getTargetProxy() {
        return targetProxy;
    }

    public void setTargetProxy(String targetProxy) {
        this.targetProxy = targetProxy;
    }

    // ===================================================================
}
