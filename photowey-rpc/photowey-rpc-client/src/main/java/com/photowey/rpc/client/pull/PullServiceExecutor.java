package com.photowey.rpc.client.pull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.photowey.rpc.annotation.client.RpcClient;
import com.photowey.rpc.client.annotation.EnableRpcClients;
import com.photowey.rpc.client.cache.ServiceRouteCache;
import com.photowey.rpc.client.properties.RpcClientProperties;
import com.photowey.rpc.client.util.ZkClientHolder;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import com.photowey.rpc.core.util.RpcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * the pull service info executor
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Component
public class PullServiceExecutor implements ServiceExecutor, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(PullServiceExecutor.class);

    @Autowired
    private ServiceRouteCache serviceRouteCache;

    @Autowired
    private ZkClientHolder zkClientHolder;
    @Autowired
    private RpcClientProperties rpcClientProperties;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void pullService() throws IOException {
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        Set<String> basePackages = this.determineTargetPackages();

        for (String basePackage : basePackages) {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    RpcUtils.resolveBasePackage(basePackage) + '/' + RpcUtils.DEFAULT_RESOURCE_PATTERN;
            Resource[] resources = pathMatchingResourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    if (!classMetadata.isInterface()) {
                        // skip current class, if not a Interface
                        continue;
                    }
                    this.pullServiceCache(classMetadata);
                } catch (Exception e) {
                    // Ignore
                }
            }
        }
        log.info("--- >>> pull full services address list from zk successfully... <<< ---");
    }

    private void pullServiceCache(ClassMetadata classMetadata) throws ClassNotFoundException {
        String className = classMetadata.getClassName();
        Class<?> clazz = ClassUtils.forName(className, PullServiceExecutor.class.getClassLoader());
        if (clazz.isAnnotationPresent(RpcClient.class)) {
            RpcClient annotation = clazz.getAnnotation(RpcClient.class);
            // the service candidate name -> Nullable
            String serviceName = clazz.getName();
            List<ProviderServiceInfo> providerServices = this.zkClientHolder.getServiceInfos(serviceName);
            this.serviceRouteCache.addCache(serviceName, providerServices);
            // add listener for service node
            this.zkClientHolder.subscribe(serviceName);
            log.info("pull the service candidate name:[{}], version:[{}],service instance is:{}",
                    annotation.value(), annotation.version(),
                    JSON.toJSONString(providerServices, SerializerFeature.PrettyFormat)
            );
        }
    }

    private Set<String> determineTargetPackages() {
        Set<String> packages = new HashSet<>();
        String[] rpcClients = this.applicationContext.getBeanNamesForAnnotation(EnableRpcClients.class);
        for (String rpcClient : rpcClients) {
            Object bean = this.applicationContext.getBean(rpcClient);
            Class<?> targetClass = AopUtils.getTargetClass(bean);
            EnableRpcClients annotation = targetClass.getAnnotation(EnableRpcClients.class);
            String[] basePackage = annotation.value();
            if (RpcUtils.isNotEmpty(basePackage)) {
                packages.addAll(Arrays.asList(basePackage));
            }
        }

        // package info in yml
        String[] basePackages = this.rpcClientProperties.getClient().getApi().getPackages();
        if (RpcUtils.isNotEmpty(basePackages)) {
            packages.addAll(Arrays.asList(basePackages));
        }
        return packages;
    }
}
