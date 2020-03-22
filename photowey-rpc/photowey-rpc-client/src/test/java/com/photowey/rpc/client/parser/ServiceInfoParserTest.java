package com.photowey.rpc.client.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.photowey.rpc.core.provider.ProviderServiceInfo;
import org.junit.Test;

/**
 * ServiceInfoParserTest
 *
 * @author WcJun
 * @date 2020/02/23
 */
public class ServiceInfoParserTest {

    /**
     * the result:
     * {
     * "appName":"com.photowey.rpc.hello.service.HelloService",
     * "networkPort":9527,
     * "protocol":"me",
     * "serverIp":"192.168.0.11",
     * "serverPort":8080,
     * "timeout":30,
     * "version":"v1.0.0",
     * "weight":3
     * }
     */
    @Test
    public void testDoParse() {
        String registryInfo = "me#192.168.0.11:8080:9527@v1.0.0@30@3@com.photowey.rpc.hello.service.HelloService";
        ServiceInfoParser serviceInfoParser = new DefaultServiceInfoParser();
        ProviderServiceInfo providerServiceInfo = serviceInfoParser.doParse(registryInfo);
        System.out.println(JSON.toJSONString(providerServiceInfo, SerializerFeature.PrettyFormat));
    }

}
