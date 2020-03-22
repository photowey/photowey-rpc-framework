package com.photowey.rpc.core.cluster;

import com.photowey.rpc.core.cluster.strategy.ClusterStrategySelector;
import com.photowey.rpc.core.enums.ClusterStrategyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * the selector Engine in Cluster mode
 *
 * @author WcJun
 * @date 2020/02/22
 */
@Component
public class ClusterEngine implements Cluster, BeanPostProcessor {

    private static final Map<ClusterStrategyEnum, ClusterStrategySelector> CLUSTER_STRATEGY_SELECTOR_CACHE = new ConcurrentHashMap<>(10);
    private static final Logger log = LoggerFactory.getLogger(ClusterEngine.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        if (bean instanceof ClusterStrategySelector) {
            ClusterStrategySelector strategySelector = (ClusterStrategySelector) bean;
            log.info("put cluster strategy:[{}] into cache", targetClass.getName());
            CLUSTER_STRATEGY_SELECTOR_CACHE.put(strategySelector.strategy(), strategySelector);
        }

        return bean;
    }

    @Override
    public ClusterStrategySelector determineClusterStrategy(String clusterStrategy) {
        ClusterStrategyEnum target = ClusterStrategyEnum.fromName(clusterStrategy.toUpperCase());
        if (null == target) {
            return null;
        }
        return CLUSTER_STRATEGY_SELECTOR_CACHE.get(target);
    }
}
