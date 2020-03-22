package com.photowey.example.provider;

import com.photowey.rpc.server.annotation.EnableRpcServers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ProviderApplication
 *
 * @author WcJun
 * @date 2020/03/21
 * @since v1.0.0
 */
@Slf4j
@EnableRpcServers
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
