package com.photowey.rpc.core.util;

import java.util.Random;

/**
 * RandomUtils
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public final class RandomUtils {

    private static final Random RANDOM = new Random();

    private RandomUtils() {
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    public static int nextInt(int startInclusive, int endExclusive) {
        if (startInclusive < 0 || startInclusive > endExclusive) {
            throw new IllegalArgumentException("");
        }
        return startInclusive == endExclusive ? startInclusive : startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }
}
