package com.photowey.example.consumer.controller;

import com.photowey.example.api.service.HelloService;
import com.photowey.rpc.core.ioc.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author WcJun
 * @date 2020/03/22
 * @since v1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/rpc")
public class HelloController {

    // FIXME NPE
    // @Autowired
    // private HelloService helloService;

    /**
     * GET :/wey
     *
     * @param name
     * @return
     * @see * http://localhost:4399/rpc/sayHello?name=photowey
     */
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam(value = "name") String name) {
        // Introducing HelloService with lazy loading
        HelloService helloService = SpringContextHolder.getBean(HelloService.class);
        // say hello to someone,words:[I'm photowey!]
        String sayHello = helloService.sayHello(name);
        log.info("the server response is:[{}]", sayHello);
        return sayHello;
    }
}
