package com.photowey.rpc.client.cache;

import com.google.common.cache.LoadingCache;
import com.photowey.rpc.annotation.client.RpcClient;
import com.photowey.rpc.client.util.ZkClientHolder;
import com.photowey.rpc.core.exception.RpcException;
import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.RpcUtils;
import com.photowey.rpc.core.util.SystemOutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * the service route cache
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class ServiceRouteCache {

    @Autowired
    private LoadingCache<String, List<ProviderServiceInfo>> routeCache;

    @Autowired
    private ZkClientHolder zkClientHolder;

    public void addCache(String serviceKey, List<ProviderServiceInfo> routes) {
        this.routeCache.put(serviceKey, routes);
    }

    public void updateCache(String serviceKey, List<ProviderServiceInfo> routes) {
        this.routeCache.put(serviceKey, routes);
    }

    public void updateCache(Map<String, List<ProviderServiceInfo>> newServiceRoutesCache) {
        this.routeCache.invalidateAll();
        Set<Map.Entry<String, List<ProviderServiceInfo>>> entries = newServiceRoutesCache.entrySet();
        for (Map.Entry<String, List<ProviderServiceInfo>> entry : entries) {
            this.routeCache.put(entry.getKey(), entry.getValue());
        }
    }

    public List<ProviderServiceInfo> getServiceRoutes(String serviceName) {
        if (this.routeCache.size() == 0) {
            this.reloadCache();
            if (this.routeCache.size() == 0) {
                throw new RpcException(SystemOutUtils.format("not found any service can be used,the service name:[{}]", serviceName));
            }
        }
        try {
            return this.routeCache.get(serviceName);
        } catch (ExecutionException e) {
            throw new RpcException(e);
        }
    }

    private void reloadCache() {
        ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForAnnotation(RpcClient.class);
        if (RpcUtils.isEmpty(beanNamesForAnnotation)) {
            return;
        }
        for (String beanName : beanNamesForAnnotation) {
            List<ProviderServiceInfo> serviceRoutes = this.zkClientHolder.getServiceInfos(beanName);
            this.addCache(beanName, serviceRoutes);
        }
    }
}
