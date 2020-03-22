package com.photowey.rpc.core.config;

import com.photowey.rpc.core.ioc.SpringContextHolder;
import com.photowey.rpc.core.serializer.ProtostuffSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * the rpc base core module bean configuration
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
@Configuration
@Import(value = {
        SpringContextHolder.class,
        ProtostuffSerializer.class
})
public class RpcBaseCoreConfiguration {
}
