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
package com.photowey.rpc.core.util;


import org.junit.Test;

/**
 * IpUtilsTest
 *
 * @author WcJun
 * @date 2020/02/23
 */
public class IpUtilsTest {

    @Test
    public void getLocalIP() {
        // 192.168.91.1
        SystemOutUtils.println(IpUtils.getLocalIP());
    }
}
