package com.photowey.rpc.annotation.server;

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
public @interface RpcServer {

    /**
     * The value may indicate a suggestion for a logical server name,
     * to be turned into a Spring bean in case of an autodetected component.
     *
     * @return the suggested server name, if any (or empty String otherwise)
     */
    Class<?> value();

    /**
     * server version
     *
     * @return the target server version
     */
    String version() default "1.0.0";
}
