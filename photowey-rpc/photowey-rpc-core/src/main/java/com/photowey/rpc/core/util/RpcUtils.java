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

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * RpcUtils
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public class RpcUtils {

    private RpcUtils() {
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static <T> boolean isEmpty(T target) {
        if (target instanceof CharSequence) {
            return null == target || "".equals(target);
        } else {
            return null == target;
        }
    }

    public static <T> boolean isNotEmpty(T target) {
        return !isEmpty(target);
    }

    public static <T> boolean isEmpty(T[] target) {
        return null == target || 0 == target.length;
    }

    public static <T> boolean isNotEmpty(T[] target) {
        return !isEmpty(target);
    }

    public static <T> boolean isEmpty(List<T> target) {
        return null == target || 0 == target.size();
    }

    public static <T> boolean isNotEmpty(List<T> target) {
        return !isEmpty(target);
    }

    // ============================================= List

    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> target = new ArrayList<>();
        while (iterator.hasNext()) {
            target.add(iterator.next());
        }

        return target;
    }

    // ============================================= Spring

    public static String generateBeanName(String className, String candidate) {
        if (isNotEmpty(candidate)) {
            return candidate;
        }
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    public static String convertClassNameToResourcePath(String className) {
        Assert.notNull(className, "Class name must not be null");
        return className.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }

    public static String resolveBasePackage(String basePackage) {
        return convertClassNameToResourcePath(basePackage);
    }

    public static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    /**
     * The package separator character: {@code '.'}.
     */
    public static final char PACKAGE_SEPARATOR = '.';

    /**
     * The path separator character: {@code '/'}.
     */
    public static final char PATH_SEPARATOR = '/';

}
