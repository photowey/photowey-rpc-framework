package com.photowey.example.consumer;

import com.photowey.rpc.client.annotation.EnableRpcClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ConsumerApplication
 *
 * @author WcJun
 * @date 2020/03/21
 * @since v1.0.0
 */
@Slf4j
@SpringBootApplication
@EnableRpcClients(value = "com.photowey.example.api.service")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
