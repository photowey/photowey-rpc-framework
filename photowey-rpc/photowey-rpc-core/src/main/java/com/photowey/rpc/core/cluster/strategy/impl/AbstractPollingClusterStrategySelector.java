package com.photowey.rpc.core.cluster.strategy.impl;

import com.photowey.rpc.core.provider.ProviderServiceInfo;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AbstractPollingClusterStrategySelector
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public abstract class AbstractPollingClusterStrategySelector extends AbstractClusterStrategySelector {

    protected int selector = 0;
    protected int LOCK_TIME_SECONDS = 10;

    protected Lock lock = null;

    public AbstractPollingClusterStrategySelector() {
        lock = new ReentrantLock();
    }

    @Override
    public ProviderServiceInfo doSelect(List<ProviderServiceInfo> routes) {
        ProviderServiceInfo target = null;
        try {
            this.lock.tryLock(LOCK_TIME_SECONDS, TimeUnit.SECONDS);

            List<ProviderServiceInfo> reset = this.doReset(routes);
            int serviceCount = reset.size();
            if (this.selector >= serviceCount) {
                this.selector = 0;
            }
            target = reset.get(this.selector);
            this.selector++;
        } catch (Exception e) {
            log.error("select the target service exception by polling", e);
        } finally {
            this.lock.unlock();
        }

        if (target == null) {
            target = routes.get(0);
        }

        return target;
    }

    /**
     * executing decision options based on different selector algorithms
     *
     * @param routes the service list
     * @return the reset service list
     */
    public abstract List<ProviderServiceInfo> doReset(List<ProviderServiceInfo> routes);
}
