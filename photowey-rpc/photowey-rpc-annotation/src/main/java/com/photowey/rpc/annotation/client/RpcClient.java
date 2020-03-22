package com.photowey.rpc.annotation.client;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * RpcServer
 *
 * @author WcJun
 * @date 2020/02/21
 * @since 1.0.0
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcClient {
    /**
     * The name of the service with optional protocol prefix. Synonym for {@link #name()
     * name}. A name must be specified for all clients, whether or not a url is provided.
     * Can be specified as property key, eg: ${propertyKey}.
     *
     * @return the name of the service with optional protocol prefix
     */
    @AliasFor("name")
    String value() default "";

    /**
     * client version
     *
     * @return the target client version
     */
    String version() default "1.0.0";

    /**
     * The service id with optional protocol prefix. Synonym for {@link #value() value}.
     *
     * @return the service id with optional protocol prefix
     * @deprecated use {@link #name() name} instead
     */
    String serviceId() default "";

    /**
     * This will be used as the bean name instead of name if present, but will not be used as a service id.
     *
     * @return bean name instead of name if present
     */
    String contextId() default "";

    /**
     * @return The service id with optional protocol prefix. Synonym for {@link #value() value}.
     */
    @AliasFor("value")
    String name() default "";

    /**
     * the expect proxy type
     * now:
     * jdk
     * cglib
     *
     * @return the expect proxy type
     */
    String targetProxy() default "cglib";

    /**
     * List of classes annotated with @RpcClient. If not empty, disables classpath scanning
     *
     * @return list of RpcClient classes
     */
    Class<?>[] clients() default {};

    /**
     * @return whether to mark the rpc proxy as a primary bean. Defaults to true.
     */
    boolean primary() default true;
}
