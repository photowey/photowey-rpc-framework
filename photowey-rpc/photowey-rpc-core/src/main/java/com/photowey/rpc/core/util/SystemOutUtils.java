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

/**
 * Use System.out.print*()
 * <p>
 * examples
 * <p>
 * String message1 = "Abc%dLINE%f%s";
 * String message2 = "Abc%dLINE%f%s";
 * String message3 = "Abc%dLINE%f%s";
 * String message4 = "Abc%dLINE%f%s";
 * SystemOutUtils.print(message1, 1, 1.1F, "1111111111111111111111111");
 * System.out.println("----------------------------------------------------------------------");
 * SystemOutUtils.print(message2, 2, 2.2F, "2222222222222222");
 * System.out.println("----------------------------------------------------------------------");
 * SystemOutUtils.println(message3, 3, 3.3F, "33333333333333");
 * System.out.println("----------------------------------------------------------------------");
 * SystemOutUtils.printf(message4, 4, 4.4F, "444444444444444");
 * System.out.println("----------------------------------------------------------------------");
 * <p>
 * the sout:
 * Abc1LINE1.1000001111111111111111111111111----------------------------------------------------------------------
 * Abc2LINE2.2000002222222222222222----------------------------------------------------------------------
 * Abc3LINE3.30000033333333333333
 * ----------------------------------------------------------------------
 * Abc4LINE4.400000444444444444444
 * ----------------------------------------------------------------------
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public final class SystemOutUtils {

    private SystemOutUtils() {
        throw new AssertionError("No " + this.getClass().getName() + " instances for you!");
    }

    // ===================================================================

    public static void print(String message, Object... params) {
        System.out.print(String.format(convertToLoggerStyle(message), params));
    }

    // ===================================================================

    public static void println(String message, Object... params) {
        System.out.println(String.format(convertToLoggerStyle(message), params));
    }

    // ===================================================================

    public static void printf(String message, Object... params) {
        System.out.printf(String.format(convertToLoggerStyle(message), params));
    }

    public static void printfln(String message, Object... params) {
        System.out.printf(String.format(convertToLoggerStyle(message), params));
        System.out.println();
    }

    // ===================================================================

    /**
     * String text = SystemOutUtils.format("Usage this method is the same as [{}]", "org.slf4j.Logger");
     * ->
     * Usage this method is the same as [org.slf4j.Logger]
     *
     * @param text   the format message
     * @param params the format params
     * @return
     */
    public static String format(String text, Object... params) {
        return String.format(convertToLoggerStyle(text), params);
    }

    public static String convertToLoggerStyle(String text) {
        // Usage this method is the same as org.slf4j.Logger
        return text.replaceAll("\\[\\{", "[%s").replaceAll("}]", "]");
    }

    // ===================================================================

    public static <T> void printLns(String[] args, Class<T> clazz) {
        if (null != args && 0 < args.length) {
            for (int i = 0; i < args.length; i++) {
                SystemOutUtils.println("the current [%s] cmd arg:[%d], value is:[%s]", clazz.getSimpleName(), i, args[i]);
            }
        }
    }
}
