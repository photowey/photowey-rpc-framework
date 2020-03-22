package com.photowey.example.provider.service.impl;

import com.photowey.rpc.annotation.server.RpcServer;
import com.photowey.example.api.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * HelloServiceImpl
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Service
@RpcServer(value = HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String words) {
        return String.format("say hello to someone,words:[%s]", "I'm photowey!");
    }
}
