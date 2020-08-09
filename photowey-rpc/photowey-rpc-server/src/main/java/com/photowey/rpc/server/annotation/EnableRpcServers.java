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
