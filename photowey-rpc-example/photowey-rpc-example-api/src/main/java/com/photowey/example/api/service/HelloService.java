package com.photowey.example.api.service;

import com.photowey.rpc.annotation.client.RpcClient;

/**
 * HelloService
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@RpcClient(value = "helloService")
public interface HelloService {

    String sayHello(String words);
}
