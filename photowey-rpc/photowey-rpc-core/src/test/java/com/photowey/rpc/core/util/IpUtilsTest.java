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
