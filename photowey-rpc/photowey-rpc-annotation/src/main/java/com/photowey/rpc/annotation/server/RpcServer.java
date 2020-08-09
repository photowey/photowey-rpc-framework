/*
 * Copyright © 2020 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
