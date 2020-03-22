package com.photowey.rpc.server.annotation;

import com.photowey.rpc.server.config.RpcServerConfiguration;
import com.photowey.rpc.server.register.RpcServersRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableRpcServers
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {RpcServersRegistrar.class, RpcServerConfiguration.class})
public @interface EnableRpcServers {
}
