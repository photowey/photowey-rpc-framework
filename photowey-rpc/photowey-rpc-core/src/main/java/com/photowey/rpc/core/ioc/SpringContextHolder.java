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
package com.photowey.rpc.core.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 以静态变量保存 Spring ApplicationContext
 * 可在任何代码任何地方任何时候取出 ApplicationContext
 *
 * @author Zaric
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(SpringContextHolder.class);

    /**
     * IOC 上下文
     */
    private static ApplicationContext applicationContext = null;

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        SpringContextHolder.assertContextInjected();

        return applicationContext;
    }

    /**
     * 从静态变量 applicationContext 中取得Bean, 自动转型为所赋值对象的类型
     *
     * @param name Bean name
     * @param <T>  泛型
     * @return T 类型
     */
    public static <T> T getBean(String name) {
        SpringContextHolder.assertContextInjected();

        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量 applicationContext 中取得Bean, 自动转型为所赋值对象的类型
     *
     * @param name  Bean userName
     * @param clazz Bean Type
     * @param <T>   泛型
     * @return T 类型
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        SpringContextHolder.assertContextInjected();

        return applicationContext.getBean(name, clazz);
    }

    /**
     * 从静态变量 applicationContext 中取得Bean, 自动转型为所赋值对象的类型
     *
     * @param clazz Bean Type
     * @param <T>   泛型
     * @return T 类型
     */
    public static <T> T getBean(Class<T> clazz) {
        SpringContextHolder.assertContextInjected();

        return applicationContext.getBean(clazz);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        log.debug("clear the SpringContextHolder context,the ApplicationContext is:{}", applicationContext);
        applicationContext = null;
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationClass) {
        return applicationContext.getBeansWithAnnotation(annotationClass);
    }


    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    /**
     * 检查 ApplicationContext 不为空.
     */
    private static void assertContextInjected() {
        assert applicationContext != null;
    }
}